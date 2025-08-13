package net.potatanata.arcanearmaments.client;

public class ScreenShake {
    public static int shakeTicks = 0;
    public static float intensity = 0.0F;

    public static void startShake(int ticks, float strength) {
        shakeTicks = ticks;
        intensity = strength;
    }

    public static void tick() {
        if (shakeTicks > 0) {
            shakeTicks--;
        }
    }

    public static float getOffset() {
        // Diminish over time
        return (shakeTicks > 0) ? (intensity * (shakeTicks / 10F)) : 0.0F;
    }
}