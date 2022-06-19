package util;

public class ArrayBox implements Box{
    //用来存储数组的默认长度；
    private static final int DEFAULT_CAPACITY = 10;

    //源数组
    private int[] elementData;
    //用来存储有效元素个数
    private int size = 0;

    // 构造方法重载
    public ArrayBox(){
        elementData = new int[DEFAULT_CAPACITY];
    }
    public ArrayBox(int capacity){
        elementData = new int[capacity];
    }


    //用来确保数组中的长度是否够用
    private void ensureCapacityInternal(int minCapacity){
        // 判断用户传进来的所需要的数组长度是否大于源数组长度，如果大于，则需要对数组进行扩容，如果小于，则不需要对源数组进行扩容
        if(minCapacity - elementData.length > 0){
            // 需要进行数组扩容
            this.grow(minCapacity);
        }
    }
    // 对源数组进行扩容
    private void grow(int minCapacity){
        // 获取源数组长度
        int oldCapacity = elementData.length;
        // 将源数组长度扩容1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 看看扩容后的长度是否小于源数组长度
        if(newCapacity - minCapacity < 0){
            newCapacity = minCapacity;
        }
        // 将源数组中的元素移入新数组中
        elementData = this.copyOf(elementData,newCapacity);

    }
    // 将源数组中的元素移入新数组中
    private int[] copyOf(int[] oldArray, int newCapacity){
        int[] newArray = new int[newCapacity];
        for(int i = 0; i < oldArray.length; i ++){
            newArray[i] = oldArray[i];
        }
        return newArray;
    }
    // 取数组元素：判断用户给定的index是否合法
    private void  rangeCheck(int index){
        if(index < 0 || index >= size){
            // 自定义异常说明问题
            throw new BoxIndexOutOfBoundExecption("Index" + index + ", Size" + size);
        }
    }

    // --------------------------------------------------------------------
    //向数组中增加元素，返回是否增加成功
    public boolean add (int element){
        /**
         * 1、确保数组的长度够用
         */
        this.ensureCapacityInternal(size + 1);
        elementData[size++] = element;
        return true;
    }
    //获取数组中指定位置的元素
    public int get(int index){
        // 判断用户给的index是否合法
        this.rangeCheck(index);
        // 找index对应位置的元素；
        return elementData[index];
    }
    //删除数组中指定位置的元素，并返回删掉的那个元素
    public int remove(int index){
        this.rangeCheck(index);
        // 找到index位置的元素保留起来，返回给用户
        int oldValue = elementData[index];
        // 1 2 3 4 5 6 size = 6
        // 1 2 4 5 6 6 elementData[5] = 0 size - 1
        //删除元素,从index位置开始size - 1结束，依次将后面的元素覆盖前面的元素
        for(int i = index; i < size - 1; i ++){
            elementData[i] = elementData[i + 1];
        }
        //手动删除元素，让size记录少一个
        elementData[--size] = 0;
        return oldValue;
    }
    //用来获取size有效的个数
    public int size(){
        return size;
    }
}
