package com.yxmall.platform.gauva;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @description:
 * @author: qing.wang.o
 * @create: 2019-01-15 16:26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
class TestEvent {
    private int message;

}

@Slf4j
class EventListener {
    @Getter
    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent testEvent) {
        lastMessage = testEvent.getMessage();
        System.out.println("message:"+ lastMessage);
    }
}

public class EventBusTest {

    @Test
    public void testReceiveEvent() {
        EventBus bus = new EventBus();
        EventListener listener = new EventListener();
        bus.register(listener);
        bus.post(new TestEvent(1));
        bus.post(new TestEvent(2));
        bus.post(new TestEvent(3));
    }
}