public class BinarySearch {

    enum IMPL {RECURSIVE, ITERATIVE};

    int doSearch(int[] arr, int num, IMPL impl) {
        if (impl == IMPL.RECURSIVE)
            return searchRecursive(arr, num);
        else
            return searchIterative(arr, num);
    }

    int searchIterative(int[] arr, int num) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + ((r - l) /2);
            if (arr[m] == num)
                return m;
            else if (arr[m] > num) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    int searchRecursive (int[] arr, int num) {
        return recursiveHelper(arr, 0, arr.length - 1, num);
    }

    int recursiveHelper(int[] arr, int start, int end, int num) {
        if (start > end)
            return -1;
        int m = start + (end - start)/2;
        if (arr[m] == num)
            return m;
        if (arr[m] > num) {
            return recursiveHelper(arr, start, m - 1, num);
        } else {
            return recursiveHelper(arr, m + 1, end, num);
        }
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] arr = new int[] {1, 4, 7, 8, 10, 15, 16, 20, 22};
        System.out.println(bs.doSearch(arr,4, IMPL.ITERATIVE));//1
        System.out.println(bs.doSearch(arr,8, IMPL.ITERATIVE));//3
        System.out.println(bs.doSearch(arr,16, IMPL.ITERATIVE));//6
        System.out.println(bs.doSearch(arr,9, IMPL.ITERATIVE));//-1

        System.out.println(bs.doSearch(arr,4, IMPL.RECURSIVE));//1
        System.out.println(bs.doSearch(arr,8, IMPL.RECURSIVE));//3
        System.out.println(bs.doSearch(arr,16, IMPL.RECURSIVE));//6
        System.out.println(bs.doSearch(arr,9, IMPL.RECURSIVE));//-1

        arr = new int[] {1, 2, 5};
        System.out.println(bs.doSearch(arr,1, IMPL.ITERATIVE));//0
        System.out.println(bs.doSearch(arr,3, IMPL.ITERATIVE));//-1

        System.out.println(bs.doSearch(arr,1, IMPL.RECURSIVE));//0
        System.out.println(bs.doSearch(arr,3, IMPL.RECURSIVE));//-1
    }
}
