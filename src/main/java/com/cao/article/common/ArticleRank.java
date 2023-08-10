package com.cao.article.common;

import cn.hutool.core.date.DateUtil;
import com.cao.article.entity.Article;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: ArticleRank
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/9 22:26
 */
@Component
public class ArticleRank {
    private final Map<String, TreeSet<Node>> map;

    ArticleRank() {
        map = new ConcurrentHashMap<>();
    }

    public void addArticle(String issueName,Article article){
        if (map.containsKey(issueName)){

            map.get(issueName).add(new Node(article));
        }else{
            TreeSet<Node> set = new TreeSet<>((a, b) -> {
                if (a.getViewCount() == b.getViewCount()) {
                    if (DateUtil.compare(b.getLastViewTime(), a.getLastViewTime())==0){
                        return b.getName().compareTo(a.getName());
                    }else{
                        return DateUtil.compare(b.getLastViewTime(), a.getLastViewTime());
                    }
                } else {
                    return b.getViewCount() - a.getViewCount();
                }
            });
            set.add(new Node(article));
            map.put(issueName,set);
        }
    }

    public void delArticle(String issueName,Article article){
        Node node = new Node(article);
        boolean remove = map.get(issueName).remove(node);
        MyLogger.getLogger().info(String.valueOf(remove));
    }

    public List<Node> getRank(String issueName){
        // 获取前三个
        if (!map.containsKey(issueName)){
            return new ArrayList<>();
        }else{
            List<Node> list = new ArrayList<>();
            TreeSet<Node> nodes = map.get(issueName);
            if (nodes==null){
                return list;
            }
            Node curr = nodes.first();
            for (int i=0;i<3;i++){
                if (curr!=null){
                    list.add(curr);
                    curr = nodes.higher(curr);
                }
            }
            return list;
        }
    }

    public void removeKey(String issueName){
        if (map.containsKey(issueName)){
            map.get(issueName).clear();
        }
    }

    public void changeKey(String issueName,String toName){
        TreeSet<Node> set = map.get(issueName);
        if (set!=null){
            this.removeKey(issueName);
            map.put(toName,set);
        }

    }
    @Data
    private static class Node{
        private int id;
        private String name;
        private Date lastViewTime;
        private int viewCount;


        Node(){

        }
        Node(Article article){
            this.id = article.getId();
            this.name = article.getName();
            this.lastViewTime = article.getLastViewTime();
            this.viewCount = article.getViewCount();
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return id == node.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
