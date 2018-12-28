package com.ibioja.magosla.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class EndpointAsyncTest {

    //Context context;
    @Test
    public void testResult() throws InterruptedException {
        assertTrue(true);
        final CountDownLatch mLatch = new CountDownLatch(1);
        //context = InstrumentationRegistry.getContext();
        EndpointsAsyncTask testTask = new EndpointsAsyncTask(null) {
            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                if (result != null){
                    assertTrue("Result must not be empty", !result.isEmpty());
                    mLatch.countDown();
                }
            }
        };
        testTask.execute();
        mLatch.await();
    }
}
