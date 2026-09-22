public class TplClass1900 {

    private static final void method(int rightoff, float[] left, float[] src, int leftoff, int srcoff, float[] right) throws Throwable {
        float x1 = src[srcoff + 0];
        float y1 = src[srcoff + 1];
        float ctrlx = src[srcoff + 2];
        float ctrly = src[srcoff + 3];
        float x2 = src[srcoff + 4];
        float y2 = src[srcoff + 5];
        if (left != null) {
            left[leftoff + 0] = x1;
            left[leftoff + 1] = y1;
        }
        if (right != null) {
            right[rightoff + 4] = x2;
            right[rightoff + 5] = y2;
        }
        x1 = (x1 + ctrlx) / 2f;
        y1 = (y1 + ctrly) / 2f;
        x2 = (x2 + ctrlx) / 2f;
        y2 = (y2 + ctrly) / 2f;
        ctrlx = (x1 + x2) / 2f;
        ctrly = (y1 + y2) / 2f;
        if (left != null) {
            left[leftoff + 2] = x1;
            left[leftoff + 3] = y1;
            left[leftoff + 4] = ctrlx;
            left[leftoff + 5] = ctrly;
        }
        if (right != null) {
            right[rightoff + 0] = ctrlx;
            right[rightoff + 1] = ctrly;
            right[rightoff + 2] = x2;
            right[rightoff + 3] = y2;
        }
    }
}

