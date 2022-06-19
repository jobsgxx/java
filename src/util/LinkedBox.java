package util;

public class LinkedBox implements Box{
    private Node first;// 记录首节点
    private  Node last;// 记录尾节点
    private int size;// 记录有效元素个数

    // 将给定的元素添加到新的Node节点里，并挂到链表的尾端
    private void linkLast(int element){
        /**
         *
         * */
        //获取链表尾节点
        Node l = last;
        //创建一个新Node对象 将新数据包装起来
        Node newNode = new Node(l,element,null);
        //将新节点设置为尾节点
        last = newNode;
        //严谨判断当前节点之前是否有节点；
        if(l == null){
            first = newNode;
        }else{
            l.next = newNode;
        }
        // 有效元素加一个
        size ++;
    }
    // 检测index是否合法
    private void  rangeCheck(int index){
        if(index < 0 || index >= size){
            // 自定义异常说明问题
            throw new BoxIndexOutOfBoundExecption("Index" + index + ", Size" + size);
        }
    }
    // 找index对应的对象
    private Node findNode(int index){
        Node targetNode;
        //判断index是在前半段还是在后判断
        if(index < (size >> 1)){
            targetNode = first;
            for(int i = 0; i < index; i ++){
                targetNode = targetNode.next;
            }
        }else{
            targetNode = last;
            for(int i = size - 1; i > index; i --){
                targetNode = targetNode.prev;
            }
        }

        return targetNode;
    }
    // 删除targetNode
    private int unlink(Node targetNode){
        int oldValue = targetNode.item;
        //当前node的前一个节点
        Node prev = targetNode.prev;
        //当前node的后一个节点
        Node next = targetNode.next;
        //删除节点对象
        if(prev == null){
            first = next;
        }else {
            prev.next = next;
            targetNode.prev = null;
        }
        if(next == null){
            last = prev;
        }else {
            next.prev = prev;
            targetNode.next = null;
        }
        //让有效元素减少一个
        size --;
        return oldValue;
    }

    //--------------------------------------------------------------------------
    //数据结构
    public boolean add(int element) {
        // 将element存入到新的Node对象里，添加至链表尾端
        this.linkLast(element);
        //告知添加成功
        return true;
    }

    public int get(int index) {
        //检测index是否合法
        this.rangeCheck(index);
        //找寻index节点对应的对象，将对象对应的数据取出返回
        Node targetNode = this.findNode(index);
        //返回找到的数据
        return targetNode.item;
    }

    public int remove(int index) {
        //检测index是否合法
        this.rangeCheck(index);
        //找到index的对象
        Node targetNode = this.findNode(index);
        //int oldValue = targetNode.item;
        //删除节点
        int oldValue = this.unlink(targetNode);
        return oldValue;
    }

    public int size() {
        return size;
    }
}
