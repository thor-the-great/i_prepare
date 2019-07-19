public class SwapOddEvenBits {

    public int swapOddEvenBits(int x) {
        //mask all odd bits
        int odd_masked = x & 0b01010101;
        //mask all even bits
        int even_masked = x & 0b10101010;
        //do the shift for odd - make it even
        odd_masked<<=1;
        //do the right for even - make it odd
        even_masked>>=1;
        //do the result by OR (|) both parts
        return (odd_masked | even_masked);
    }
}
