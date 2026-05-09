package rip.ysm.compat.oculus.fabric;

public final class OculusCompatImpl {

    private OculusCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static boolean isPBRActive() {
        return false;
    }

    public static void updatePBRState() {
    }
}
