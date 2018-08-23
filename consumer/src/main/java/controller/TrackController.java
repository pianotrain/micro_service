package controller;

import com.rensm.api.IAuditTracker;
import com.rensm.api.exception.InternalServiceException;
import com.rensm.api.model.RecordItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrackController {

    @Autowired
    private IAuditTracker auditTracker;

    @RequestMapping("/check_health")
    public String checkHealth(){

        return "ok";
    }

    @RequestMapping("/audit")
    public List<RecordItem> audit() throws InternalServiceException {
        List<RecordItem> recordBySource =
                auditTracker.getRecordBySource("1");
        return recordBySource;
    }
}
