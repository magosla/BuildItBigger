package com.ibioja.magosla.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class EndpointAsyncTest {
    @Test
    public void testResult() throws ExecutionException, InterruptedException {
        String result = new EndpointAsyncTask(null).execute().get();
        assertNotNull(result);
        assertTrue("Result must not be empty", !TextUtils.isEmpty(result));
    }



    /*
    @Test
    public void testResult() throws InterruptedException {
        final CountDownLatch mLatch = new CountDownLatch(1);
        //context = InstrumentationRegistry.getContext();
        EndpointAsyncTask testTask = new EndpointAsyncTask(null) {
            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                assertTrue("Result must not be empty", !result.isEmpty());
                mLatch.countDown();
            }
        };
        testTask.execute();
        mLatch.await();
    }
    */
}
