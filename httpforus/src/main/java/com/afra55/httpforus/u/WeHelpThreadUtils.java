package com.afra55.httpforus.u;

/**
 * @author Afra55
 * @date 2019-12-09
 * A smile is the best business card.
 */

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class WeHelpThreadUtils {
    private static final Handler uiHandler;
    private static final ExecutorService workerExecutor;
    private static final Handler workerHandler;

    static {
        uiHandler = new Handler(Looper.getMainLooper());
        HandlerThread handlerThread = new HandlerThread(WeHelpThreadUtils.class.getName());
        handlerThread.start();
        workerHandler = new Handler(handlerThread.getLooper());
        workerExecutor = Executors.newCachedThreadPool();
    }

    public WeHelpThreadUtils() {
    }

    public static void postOnUiThread(Runnable runnable) {
        uiHandler.post(runnable);
    }

    public static void runOnUiThread(Runnable runnable) {
        postOnUiThread(runnable);
    }

    public static void runOffUiThread(Runnable runnable) {
        if (isUiThread()) {
            workerExecutor.execute(runnable);
        } else {
            runnable.run();
        }

    }

    public static void runOnWorkerThread(Runnable runnable) {
        workerExecutor.execute(runnable);
    }

    public static WeHelpThreadUtils.ScheduledRunnable runOnUiThreadDelayed(final Runnable runnable, long delay) {
        WeHelpThreadUtils.ScheduledRunnable runnableWrapper = new WeHelpThreadUtils.ScheduledRunnable() {
            public void run() {
                runnable.run();
            }

            public void cancel() {
                WeHelpThreadUtils.uiHandler.removeCallbacks(this);
            }
        };
        uiHandler.postDelayed(runnableWrapper, delay);
        return runnableWrapper;
    }

    public static WeHelpThreadUtils.ScheduledRunnable runOnWorkerThreadDelayed(final Runnable runnable, long delay) {
        WeHelpThreadUtils.ScheduledRunnable runnableWrapper = new WeHelpThreadUtils.ScheduledRunnable() {
            public void run() {
                WeHelpThreadUtils.workerExecutor.execute(runnable);
            }

            public void cancel() {
                WeHelpThreadUtils.workerHandler.removeCallbacks(this);
            }
        };
        workerHandler.postDelayed(runnableWrapper, delay);
        return runnableWrapper;
    }

    public static boolean isUiThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static int getActiveWorkerThreadCount() {
        return ((ThreadPoolExecutor)workerExecutor).getActiveCount();
    }

    public interface ScheduledRunnable extends Runnable {
        void cancel();
    }
}
