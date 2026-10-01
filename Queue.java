public class Queue
{
    private int[] data;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    //Constructors

    public Queue()
    {
        this(100);
    }

    public Queue(int capacity)
    {
        this.capacity = capacity;
        data = new int[capacity];
        head = 0;
        tail = 0;
        size = 0; //At first size equals zero since the queue doesn't have data. It will change when we add numbers to the queue.
    }

    //Methods

    //We use the insert method to add a number to the end of our existing queue.

    public boolean insert(int num)
    {
        //We use the if statement to determine whether or not we have adequate space to add the number num to our queue.
        //When size == capacity the number of elements the queue has is the maximum the capacity allows, meaning we can't add more.

        if (size == capacity)
        {
            return false;
        }
        
        data[tail] = num; //It adds the new number (num) on the place the tail shows, meaning in the last spot of the queue.
        tail = (tail + 1) % capacity; //We use the modulo with the tail so our queue won't break and will be circular.
        size++;

        return true;
    }

    //On the other hand we use remove in order to discard the first integer of our existing queue.

    public int remove()
    {
        //If our queue doesn't have numbers this if statement will return the mimimu integer value of Java.
        if (size == 0)
        {
            return Integer.MIN_VALUE;
        }

        int value = data[head];
        head = (head + 1) % capacity;
        size--;

        return value;
    }

    //Merge's purpose is to add the data of another queue to the queue of the method.
    //It changes the queue of the method by adding that data directly.

    public boolean merge(Queue other)
    {
        //Since we are adding data to the existing queue we want to make sure that the sum of the two sizes won't surpass the capacity of the original queue.

        if (this.size + other.size > this.capacity)
        {
            return false;
        }

        //This for loop is use so we can add in a circular way every number of the other queue in this one.

        for (int i = 0; i < other.size; i++)
        {
            int pointer = (other.head + i ) % other.capacity;
            int clue = other.data[pointer];
            this.insert(clue);
        }

        return true;
    }

    //Concatenate on the other hand takes the two queues and creates a new queue by adding the data of the first and the second queue.
    //None of the original queues are altered, their data are simply used to create a brand new queue.

    public Queue concatenate(Queue other)
    {
        Queue result = new Queue(this.capacity + other.capacity);

        for (int i = 0; i < this.size; i++)
        {
            int pointer1 = (this.head + i) % this.capacity;
            result.insert(this.data[pointer1]);
        }

        for (int i = 0; i < other.size; i++)
        {
            int pointer2 = (other.head + i) % other.capacity;
            result.insert(other.data[pointer2]);
        }

        return result;
    }

    //Invert is going to take a queue and return it's inverted version. 

    public Queue invert()
    {
        Queue inverted = new Queue(this.capacity);

        int[] temporary = new int[this.size];

        for (int i = 0; i < this.size; i++)
        {
            int pointer = (this.head + i) % this.capacity;
            temporary[i] = this.data[pointer];
        }

        for (int i = this.size - 1; i >= 0; i--)
        {
            inverted.insert(temporary[i]);
        }

        return inverted;
    }

    //toString will converted our queue to a string.

    public String toString()
    {
        if (this.size ==0)
        {
            return "[]";
        }

        String str = "[";

        for (int i = 0; i < this.size; i++)
        {
            int pointer = (this.head + i) % this.capacity;
            str += this.data[pointer];

            if (i < this.size - 1)
            {
                str += ", ";
            }
        }
        
        str += "]";
        return str;
    }

    //Equals is going to check whether or not two different queues are equal to one another. This queues shouldn't only have the same numbers (and sizes) but also capacities.

    public boolean equals(Queue other)
    {

        //This if checks that we are in fact about to compare to queues.

        if (other == null)
        {
            return false;
        }

        //This is checks if the two queues have the same capacity.

        if (this.capacity != other.capacity)
        {
            return false;
        }

        //And this checks whether or not they have the same size.

        if (this.size != other.size)
        {
            return false;
        }

        //We use the for loop to check if the queues hold the same data in the same FIFO placements.

        for (int i = 0; i < size; i++)
        {
            int pointer1 = (this.head + i) % this.capacity;
            int pointer2 = (other.head + i) % other.capacity;

            if (this.data[pointer1] != other.data[pointer2])
            {
                return false;
            }
        }

        return true;
    }
    
    public boolean isEmpty()
    {
        return size ==0;
    }

    //The size method simply returns the size of the queue.

    public int size()
    {
        return size;
    }

    public static void main(String[] args)
    {
        System.out.println("Hello, this is a Java Queue exercise");
        System.out.println("Author: 5802");

        Queue q1 = new Queue(); //Queue without given capacity, checks the 1st constructor.
        Queue q2 = new Queue(7); //Queue with given capacity checks the 2nd constructor.

        System.out.println("~~~~~~~Let's test our methods!~~~~~~~");
        System.out.println("~~~~~~~Let's test isEmpty first!~~~~~~~");

        System.out.println("Is Queue 1 empty?" + q1.isEmpty());
        System.out.println("Is Queue 2 empty?" + q2.isEmpty());

        System.out.println("~~~~~~~Now let's test insert by adding numbers to Queue 2! (We are also testing toString here)~~~~~~~");

        q2.insert(71);
        q2.insert(72);
        q2.insert(73);
        q2.insert(74);
        q2.insert(75);
        q2.insert(76);
        q2.insert(77); //I fill up q2 until it has the same numbers as it does capacity.

        System.out.println("Queue 2 after the insert: " + q2);
        System.out.println("Queue 2 has a capacity of 7 and already has 7 numbers so it's full.");
        System.out.println("Let's try to insert one more number: " + q2.insert(78)); //This shpuld fail.

        System.out.println("~~~~~~~Let's test remove!~~~~~~~");

        System.out.println("Removing..." + q2.remove());
        System.out.println("Removing..." + q2.remove());

        System.out.println("Queue 2 after removing 2 numbers: " + q2);

        q2.remove();
        q2.remove();
        q2.remove();
        q2.remove();
        q2.remove();

        System.out.println("Let's try to remove from Queue 2 (which should be empty): " + q2.remove()); //This shuld also fail because q2 is empty now.

        System.out.println("~~~~~~~Let's test merge!~~~~~~~");

        Queue q3 = new Queue(5);
        Queue q4 = new Queue(5);

        q3.insert(1);
        q3.insert(2);
        q3.insert(3);

        q4.insert(4);
        q4.insert(5);

        System.out.println("Queue 3 is: " + q3);
        System.out.println("Queue 4 is: " + q4);

        System.out.println("Merge result: " + q3.merge(q4));
        System.out.println("Queue 3 after merging is: " + q3);
        System.out.println("Let's try to merge a second time (the size would be bigger than the capacity so this should fail)" + q3.merge(q4)); //This fails because this.size + other.size > this.capacity.

        System.out.println("~~~~~~~Let's test concatenate!~~~~~~~");

        Queue q5 = q3.concatenate(q4);

        System.out.println("The concatenated queue (Queue 5) is: " + q5);

        System.out.println("~~~~~~~Let's now test invert!~~~~~~~");

        Queue invq5 = q5.invert();

        System.out.println("Queue 5 (the concatenated queue) is: " + q5);
        System.out.println("Queue 5's inverted form: " + invq5);

        System.out.println("~~~~~~~Now we will test equals!~~~~~~");

        Queue q6 = new Queue(4);
        Queue q7 = new Queue(4);

        q6.insert(6);
        q6.insert(7);

        q7.insert(6);
        q7.insert(7);

        System.out.println("Does Queue 6 equal Queue 7?" + q6.equals(q7));

        q7.insert(8);

        System.out.println("Does Queue 6 equal Queue 7 this time around (it shouldn't since I inserted another number.)?" + q6.equals(q7));

        System.out.println("~~~~~~~Last but not least, let's test size!~~~~~~~");

        System.out.println("Size of Queue 5 (the concatenated one) is: " + q5.size());

    }
}