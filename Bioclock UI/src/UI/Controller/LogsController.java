/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Controller;

import bioclock.client.service.RMIActivityLogService;
import bioclock.dto.ActivityLogDTO;
import java.net.InetAddress;
import java.util.Date;

/**
 *
 * @author Jiggyy
 */
public class LogsController {
    
    private final RMIActivityLogService service;

    public LogsController(RMIActivityLogService service){
        this.service = service;
    }
    
    public void log(String action, String description){
        ActivityLogDTO dto = new ActivityLogDTO();
        
        dto.setAction(action);
        dto.setDescription(description);
        dto.setTimestamp(new Date());
        
        service.log(dto);
    }
    
}
