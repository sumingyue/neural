package cn.micro.neural.circuitbreaker.core;

import cn.micro.neural.circuitbreaker.CircuitBreakerContext;
import cn.micro.neural.circuitbreaker.CircuitBreakerState;
import cn.micro.neural.circuitbreaker.OriginalCall;
import cn.neural.common.extension.SPI;

/**
 * ICircuitBreaker
 *
 * @author lry
 */
@SPI("stand-alone")
public interface ICircuitBreaker {

    /**
     * 获取熔断状态
     *
     * @return {@link CircuitBreakerState}
     */
    CircuitBreakerState getState();

    // === 状态操作

    /**
     * 打开熔断
     * <p>
     * 以下几种场景会使用打开操作：
     * 1.closed->open
     * 2.half-open->open
     */
    void open();

    /**
     * 半开熔断
     * <p>
     * 以下几种场景会使用半开操作：
     * 1.open->half-open
     */
    void openHalf();

    /**
     * 关闭熔断
     * <p>
     * 以下几种场景会使用关闭操作：
     * 1.half-open->close
     */
    void close();

    // === 判断熔断状态是否该转移(即判断是否达到了转移的阈值)

    /**
     * open状态下是否可以转移至half-open状态
     * <p>
     * 原理：当前时间和最后一次打开的时间差超过指定阈值(默认为5秒),则状态可以由打开转移为半开
     *
     * @return true表示达到了转为半开状态的条件
     */
    boolean isOpen2HalfOpenTimeout();

    /**
     * close状态下是否可以转移至open状态
     * <p>
     * 原理：closed状态下判断是否超过允许的最大失败次数
     *
     * @return true表示达到了转为打开状态的条件
     */
    boolean isCloseFailThresholdReached();

    /**
     * half-open状态下是否可以转移至close状态
     * <p>
     * 原理：half-open状态下连续成功次数超过配置的阈值,则可以转移至close状态
     *
     * @return true表示达到了转为关闭状态的条件
     */
    boolean isConsecutiveSuccessThresholdReached();

    /**
     * The process of original call
     *
     * @param circuitBreakerContext {@link CircuitBreakerContext}
     * @param originalCall          {@link OriginalCall}
     * @return original call return result
     * @throws Throwable throw exception
     */
    Object originalCall(final CircuitBreakerContext circuitBreakerContext, final OriginalCall originalCall) throws Throwable;

}