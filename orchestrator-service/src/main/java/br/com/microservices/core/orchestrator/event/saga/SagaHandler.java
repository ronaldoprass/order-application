package br.com.microservices.core.orchestrator.event.saga;

import static br.com.microservices.core.orchestrator.domain.enums.EEventSource.INVENTORY_SERVICE;
import static br.com.microservices.core.orchestrator.domain.enums.EEventSource.ORCHESTRATOR;
import static br.com.microservices.core.orchestrator.domain.enums.EEventSource.PAYMENT_SERVICE;
import static br.com.microservices.core.orchestrator.domain.enums.EEventSource.PRODUCT_VALIDATION_SERVICE;
import static br.com.microservices.core.orchestrator.domain.enums.ESagaStatus.FAIL;
import static br.com.microservices.core.orchestrator.domain.enums.ESagaStatus.ROLLBACK_PENDING;
import static br.com.microservices.core.orchestrator.domain.enums.ESagaStatus.SUCCESS;
import static br.com.microservices.core.orchestrator.domain.enums.ETopics.FINISH_FAIL;
import static br.com.microservices.core.orchestrator.domain.enums.ETopics.FINISH_SUCCESS;
import static br.com.microservices.core.orchestrator.domain.enums.ETopics.INVENTORY_FAIL;
import static br.com.microservices.core.orchestrator.domain.enums.ETopics.INVENTORY_SUCCESS;
import static br.com.microservices.core.orchestrator.domain.enums.ETopics.PAYMENT_FAIL;
import static br.com.microservices.core.orchestrator.domain.enums.ETopics.PAYMENT_SUCCESS;
import static br.com.microservices.core.orchestrator.domain.enums.ETopics.PRODUCT_VALIDATION_FAIL;
import static br.com.microservices.core.orchestrator.domain.enums.ETopics.PRODUCT_VALIDATION_SUCCESS;

public final class SagaHandler {

    private SagaHandler() {

    }

    public static final Object[][] SAGA_HANDLER = {
        { ORCHESTRATOR, SUCCESS, PRODUCT_VALIDATION_SUCCESS },
        { ORCHESTRATOR, FAIL, FINISH_FAIL },

        { PRODUCT_VALIDATION_SERVICE, ROLLBACK_PENDING, PRODUCT_VALIDATION_FAIL },
        { PRODUCT_VALIDATION_SERVICE, FAIL, FINISH_FAIL },
        { PRODUCT_VALIDATION_SERVICE, SUCCESS, PAYMENT_SUCCESS },

        { PAYMENT_SERVICE, ROLLBACK_PENDING, PAYMENT_FAIL },
        { PAYMENT_SERVICE, FAIL, PRODUCT_VALIDATION_FAIL },
        { PAYMENT_SERVICE, SUCCESS, INVENTORY_SUCCESS },

        { INVENTORY_SERVICE, ROLLBACK_PENDING, INVENTORY_FAIL },
        { INVENTORY_SERVICE, FAIL, PAYMENT_FAIL },
        { INVENTORY_SERVICE, SUCCESS, FINISH_SUCCESS }
    };

    public static final int EVENT_SOURCE_INDEX = 0;
    public static final int SAGA_STATUS_INDEX = 1;
    public static final int TOPIC_INDEX = 2;
}
