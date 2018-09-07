package com.paragonsoftware.db.listener;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.paragonsoftware.db.AuditableEntity;
import com.paragonsoftware.db.History;
import com.paragonsoftware.db.repo.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Map;

import static javax.transaction.Transactional.TxType.MANDATORY;

/**
 * @author IvIsmakaev
 */
@RepositoryEventHandler(AuditableEntity.class)
public class CreateHistoryListener {
    public static final String CREATE = "create";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private HistoryRepository historyRepository;

    @HandleBeforeCreate
    public void beforeCreateHandler(final AuditableEntity entity) {
        History historyEvent = new History();
        String diffString = getDiffString(entity);

        historyEvent.setEntityId(entity.getId());
        historyEvent.setEventName(CREATE);
        historyEvent.setDataDiff(diffString);

        historyRepository.save(historyEvent);
    }

    @HandleBeforeSave
    public void beforeSaveHandler(final AuditableEntity entity) {
        String diffString = getDiffString(entity);

        History historyEvent = new History();
        historyEvent.setEntityId(entity.getId());
        historyEvent.setEventName(UPDATE);
        historyEvent.setDataDiff(diffString);

        historyRepository.save(historyEvent);
    }


    @HandleBeforeDelete
    public void beforeDeleteHandler(final AuditableEntity entity) {
        History historyEvent = new History();
        historyEvent.setEntityId(entity.getId());
        historyEvent.setEventName(DELETE);
        historyRepository.save(historyEvent);
    }

    @Transactional(MANDATORY)
    private String getDiffString(final AuditableEntity entity) {
        String diffString = null;
        entityManager.detach(entity);
        AuditableEntity oldEntity = entityManager.find(entity.getClass(), entity.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true)
                .configure(JsonParser.Feature.ALLOW_MISSING_VALUES, true)
                .configure(SerializationFeature.INDENT_OUTPUT, true);
        Map diffMap = null;
        if (oldEntity != null) {
            diffMap = entity.createDiff(oldEntity);
        } else {
            diffMap = entity.createCurrentAttributesMap();
        }
        try {
            diffString = objectMapper.writeValueAsString(diffMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return diffString;
    }
}
