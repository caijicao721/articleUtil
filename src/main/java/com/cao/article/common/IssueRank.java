package com.cao.article.common;

import cn.hutool.core.comparator.CompareUtil;
import com.cao.article.entity.Issue;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * ClassName: IssueRank
 * Description:
 *
 * @author caojiaming
 * @version 1.0
 * @Create 2023/8/10 11:55
 */
@Component
public class IssueRank{

    private final Map<String,MyLRUCache<String, Date>> map;
    public IssueRank(){
        map = new ConcurrentHashMap<>();
    }

    public void put(String username, Issue issue){
        if (map.containsKey(username)){
            map.get(username).put(issue.getName(),issue.getModifiedTime());
        }else{
            MyLRUCache<String, Date> cache = new MyLRUCache<>(ConstParam.CACHE_SIZE);
            cache.put(issue.getName(),issue.getModifiedTime());
            map.put(username,cache);
        }
    }


    public List getList(String username){
        Set<Map.Entry<String, MyLRUCache.Node>> list = map.get(username).list();
        List<Object> collect = list.stream()
                .sorted(
                        (a,b)->{
                            Date a1 = (Date)a.getValue().val;
                            Date b1 = (Date)b.getValue().val;
                            return CompareUtil.compare(b1,a1);
                        }
                )
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return collect;
    }

    public void removeIssue(String username,String issueName){
        MyLRUCache<String, Date> cache = map.get(username);
        cache.delete(issueName);
    }

    public void rename(String username,String first,String last){
        MyLRUCache<String, Date> cache = map.get(username);
        cache.rename(first,last);
    }






    private static class MyLRUCache<K,V>{
        private int size;
        private Node head;
        private Node tail;
        private Map<K,Node> map;
        MyLRUCache(int size){
            this.size = size;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.next = head;
            map = new ConcurrentHashMap<>();
        }

        public void put(K key,V val){
            if (!map.containsKey(key)){
                Node node = new Node(key,val);
                if (size == map.size()){
                    Node delNode = tail.prev;
                    delNode(tail.prev);
                    map.remove(delNode.key);
                    addNode(node);
                }else{
                    addNode(node);
                }
                map.put(key,node);
            }else{
                Node node = map.get(key);
                node.val = val;
                delNode(node);
                addNode(node);
            }
        }

        public V get(K key){
            if (!map.containsKey(key)){
                return null;
            }else{
                Node node = map.get(key);
                delNode(node);
                addNode(node);
                return (V) node;
            }
        }

        public void delete(K key){
            Node node = map.get(key);
            delNode(node);
            map.remove(key);
        }

        public void rename(K key,K toKey){
            Node node = map.get(key);
            delNode(node);
            map.remove(key);
            node.key = toKey;
            map.put(toKey,node);
            addNode(node);
        }
        public Set<Map.Entry<K, Node>> list(){
            return map.entrySet();
        }


        private static class Node<K,V>{
            private K key;
            private V val;
            private Node prev;
            private Node next;
            Node(){

            }
            Node(K key,V val){
                this.key = key;
                this.val = val;
                this.prev = null;
                this.next = null;
            }
        }

        private void addNode(Node node){
            Node next = head.next;
            head.next = node;
            node.prev = head;
            node.next = next;
            next.prev = node;
        }
        private void delNode(Node node){
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }
    }
}
