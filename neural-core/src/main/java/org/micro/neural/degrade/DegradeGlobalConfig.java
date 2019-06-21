package org.micro.neural.degrade;

import lombok.*;
import org.micro.neural.config.GlobalConfig;
import org.micro.neural.event.IEventType;

import java.io.Serializable;

/**
 * The Degrade Global Config
 *
 * @author lry
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DegradeGlobalConfig extends GlobalConfig implements Serializable {

    private static final long serialVersionUID = 3023172841450527624L;

    /**
     * The degrade level
     */
    private Level level = Level.NON;

    /**
     * The degrade level
     *
     * @author lry
     */
    @Getter
    @AllArgsConstructor
    public enum Level {

        /**
         * The no open downgrade, is non
         */
        NON(0, "The no open downgrade, is non"),
        /**
         * The may need to downgrade, is hint
         */
        HINT(1, "The may need to downgrade, is hint"),
        /**
         * The recommended to downgrade
         */
        RECOMMEND(2, "The recommended to downgrade"),
        /**
         * The need to downgrade
         */
        NEED(3, "The need to downgrade"),
        /**
         * The must to downgrade, is warning
         */
        WARN(4, "The must to downgrade, is warning"),
        /**
         * The must to downgrade, is serious
         */
        SERIOUS(5, "The must to downgrade, is serious");

        int order;
        String message;

    }

    /**
     * The Degrade Event Type.
     *
     * @author lry
     **/
    @Getter
    @AllArgsConstructor
    public enum EventType implements IEventType {

        /**
         * The notification mock data exception of degrade
         */
        NOTIFY_EXCEPTION("The notify config is exception of degrade"),
        /**
         * The collect statistics exception of degrade
         */
        COLLECT_EXCEPTION("The collect statistics is exception of degrade");

        String message;

    }

}