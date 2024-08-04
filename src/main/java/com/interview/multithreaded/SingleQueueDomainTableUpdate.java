package com.interview.multithreaded;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Given a queue which gets millions of messages. Message is of form <Domain,Update>.
 * You have 10000 domain tables. Also you have 50 worker threads. You can only get 
 * val from front of the queue. Threads get val from the front and then update the 
 * domain table. If work is being done on domain table you cannot apply another update.
 * Update should also be applied sequentially. So an update coming later on should not
 * be applied before an update coming sooner.
 */

class val{
    private String domain;
    private String update;
    public String getUpdate() {
        return update;
    }
    public String getDomain() {
        return domain;
    }
}

interface DomainLock{
    boolean acquireLock(String domain);
    boolean releaseLock(String domain);
    boolean isLocked(String domain);
}

class ThreadPoolManager{
    private ConcurrentMap<String, Queue<val>> domainQueueMap = new ConcurrentHashMap<>();
    public ThreadPoolManager(int numOfThreads){
        //initialize numOfThreads of type ThreadWorker;
    }
}

interface valbaseLayer{
    public void applyUpdates(String domain,String update);
}

class ThreadWorker implements Runnable{

    private QueueManager mgr;
    private ConcurrentMap<String,Queue<val>> domainQueueMap;
    private DomainLock domainLock;
    private valbaseLayer valbaseLayer;
    public ThreadWorker(QueueManager mgr){
        this.mgr = mgr;
    }
    @Override
    public void run() {
        while(true){
            Pair p = mgr.getvalFromFrontOfQueue(domainLock);
            if(p.yourLock){
                Queue<val> queue = domainQueueMap.get(p.val.getDomain());
                if(queue != null){
                    while(queue.size() > 0){
                        val val = queue.poll();
                        valbaseLayer.applyUpdates(val.getDomain(), val.getUpdate());
                    }
                }
                valbaseLayer.applyUpdates(p.val.getDomain(), p.val.getUpdate());
                queue = domainQueueMap.get(p.val.getDomain());
                if(queue != null){
                    while(queue.size() > 0){
                        val val = queue.poll();
                        valbaseLayer.applyUpdates(val.getDomain(), val.getUpdate());
                    }
                }
                domainLock.releaseLock(p.val.getDomain());
                //check if queue is not empty
                //if queue is not empty try to acquire lock again
            }else{
                if(domainQueueMap.containsKey(p.val.getDomain())){
                    Queue<val> queue = domainQueueMap.get(p.val.getDomain());
                    queue.offer(p.val);
                }
            }
        }
    }
    
}

interface QueueHandle{
    //this is a blocking call. If there is no val in the queue it just waits for val to be available
    public val getNextval();
}

class Pair{
    val val;
    boolean yourLock;
}

class QueueManager{
    private QueueHandle queueHandle;
    public QueueManager(QueueHandle queueHandle){
        this.queueHandle = queueHandle;
    }
    
    public synchronized Pair getvalFromFrontOfQueue(DomainLock domainLock){
        val val = queueHandle.getNextval();
        boolean yourLock = false;
        //if lock for table does not exists or if it is false lock the table
        if(!domainLock.isLocked(val.getDomain())){
            domainLock.acquireLock(val.getDomain());
            yourLock = true;
        }
        Pair p = new Pair();
        p.val = val;
        p.yourLock = yourLock;
        return p;
    }
}
public class SingleQueueDomainTableUpdate {
}
