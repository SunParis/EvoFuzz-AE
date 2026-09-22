import java.lang.invoke.VarHandle;

public class VarHandleFence {
    
    public static void $mut1() {
        VarHandle.fullFence();
    }
}