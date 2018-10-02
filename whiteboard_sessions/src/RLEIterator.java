class RLEIterator {

    int[] arr;
    int position, count;


    void init(int[] arr) {
        this.arr = arr;
        position = 0;
        count = arr[0];
    }

    public int next(int n) {
        if (position >= arr.length) {
            return -1;
        }

        while (n >= 0) {
            if (count - n >= 0) {
                count -= n;
                return arr[position + 1];
            }
            else {
                n = n - count;
                if ((position + 2) < arr.length) {
                    position += 2;
                    count = arr[position];
                } else
                    break;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        RLEIterator obj = new RLEIterator();
        obj.init(new int[]{3, 8, 0, 9, 2, 5});
        System.out.println(obj.next(2));
        System.out.println(obj.next(1));
        System.out.println(obj.next(2));
        System.out.println(obj.next(5));
    }
}