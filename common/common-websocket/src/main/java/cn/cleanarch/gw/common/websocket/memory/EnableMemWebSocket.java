package cn.cleanarch.gw.message.configuration.websocket.memory;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author lihaifeng at 2018/10/15
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({MemoryWebSocketConfiguration.class})
public @interface EnableMemWebSocket {
}
