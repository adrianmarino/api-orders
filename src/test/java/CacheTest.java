import com.navent.api.orders.cache.Cache;
import com.navent.api.orders.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
class CacheTest {

    @Test
    @DisplayName("when get entity from empty cache it add entity to cache")
    void test1() {
        // Prepare
        Order order = new Order("1", "Order 1", BigDecimal.valueOf(1000), BigDecimal.valueOf(100));
        AtomicBoolean callbackCalled = new AtomicBoolean();
        Cache<Order> cache = new Cache<>();

        // Perform
        Optional<Order> saveOrder = cache.get(order.getId(), () -> {
            callbackCalled.set(true);
            return Optional.of(order);
        });

        // Assert
        assertThat(callbackCalled.get(), is(equalTo(true)));
        assertThat(saveOrder.isPresent(), is(equalTo(true)));
        assertThat(saveOrder.get(), is(equalTo(order)));
        assertThat(cache.entriesCount(), is(equalTo(1)));
    }

    @Test
    @DisplayName("when get entity that already exist in cache it does not add entity to cache")
    void test2() {
        // Prepare
        Order order = new Order("1", "Order 1", BigDecimal.valueOf(1000), BigDecimal.valueOf(100));
        AtomicBoolean callbackCalled = new AtomicBoolean(false);
        Cache<Order> cache = new Cache<>();
        cache.get(order.getId(), () -> Optional.of(order));

        // Perform
        Optional<Order> saveOrder = cache.get(order.getId(), () -> {
            callbackCalled.set(true);
            return Optional.of(order);
        });

        // Assert
        assertThat(callbackCalled.get(), is(equalTo(false)));
        assertThat(saveOrder.isPresent(), is(equalTo(true)));
        assertThat(saveOrder.get(), is(equalTo(order)));
        assertThat(cache.entriesCount(), is(equalTo(1)));
    }

    @Test
    @DisplayName("when add entity to cache it get entities count")
    void test3() {
        // Prepare
        Order order = new Order("1", "Order 1", BigDecimal.valueOf(1000), BigDecimal.valueOf(100));
        Cache<Order> cache = new Cache<>();
        cache.get(order.getId(), () -> Optional.of(order));

        // Perform
        Integer count = cache.entriesCount();

        // Assert
        assertThat(count, is(equalTo(1)));
    }

    @Test
    @DisplayName("when clean not empty cache it remove all entities")
    void test4() {
        // Prepare
        Order order = new Order("1", "Order 1", BigDecimal.valueOf(1000), BigDecimal.valueOf(100));
        Cache<Order> cache = new Cache<>();
        cache.get(order.getId(), () -> Optional.of(order));

        // Perform
        cache.clean();

        // Assert
        assertThat(cache.entriesCount(), is(equalTo(0)));
    }

    @Test
    @DisplayName("when try to get entity that does not exist in cache/database it does not add nothing to cache")
    void test5() {
        // Prepare
        AtomicBoolean callbackCalled = new AtomicBoolean(false);
        Cache<Order> cache = new Cache<>();

        // Perform
        Optional<Order> saveOrder = cache.get("anyId", () -> {
            callbackCalled.set(true);
            return empty();
        });

        // Assert
        assertThat(callbackCalled.get(), is(equalTo(true)));
        assertThat(saveOrder.isPresent(), is(equalTo(false)));
        assertThat(cache.entriesCount(), is(equalTo(0)));
    }
}
