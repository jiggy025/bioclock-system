/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import bioclock.dto.ActivityLogDTO;
import bioclock.server.entity.ActivityLog;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jiggyy
 */

public class ActivityLogServiceImpl implements IActivityLogService {

    
    private final IActivityLogRepository repository;
    
    
    public ActivityLogServiceImpl(IActivityLogRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public void log(ActivityLogDTO dto) {
        ActivityLog log = new ActivityLog();
        
        log.setAction(dto.getAction());
        log.setDescription(dto.getDescription());
        log.setTimestamp(dto.getTimestamp());
        
        repository.save(log);
    }

    @Override
    public List<ActivityLogDTO> getLogs() {
                List<ActivityLog> logs = repository.findAll();

        List<ActivityLogDTO> result = new ArrayList<>();

        for (ActivityLog log : logs) {

            ActivityLogDTO dto = new ActivityLogDTO();
            dto.setAction(log.getAction());
            dto.setDescription(log.getDescription());
            dto.setTimestamp(log.getTimestamp());

            result.add(dto);
        }

        return result;
    }
    
}
