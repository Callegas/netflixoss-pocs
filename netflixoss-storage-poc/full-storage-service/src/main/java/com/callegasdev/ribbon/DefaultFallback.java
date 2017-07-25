package com.callegasdev.ribbon;

import com.netflix.hystrix.HystrixInvokableInfo;
import com.netflix.ribbon.hystrix.FallbackHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.timeout.TimeoutException;
import rx.Observable;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class DefaultFallback implements FallbackHandler<ByteBuf> {

	@Override
	public Observable<ByteBuf> getFallback(HystrixInvokableInfo<?> hystrixInfo, Map<String, Object> requestProperties) {
		return Observable.from(new java.util.concurrent.Future<ByteBuf>() {

			ByteBuf buf = Unpooled.wrappedBuffer(("Error").getBytes());

			public boolean cancel(boolean mayInterruptIfRunning) {
				return false;
			}

			public boolean isCancelled() {
				return false;
			}

			public boolean isDone() {
				return true;
			}

			public ByteBuf get() throws InterruptedException, ExecutionException {
				return buf;
			}

			public ByteBuf get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException, TimeoutException {
				return buf;
			}
		});
	}
}