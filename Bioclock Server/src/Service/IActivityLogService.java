/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import bioclock.dto.ActivityLogDTO;
import java.util.List;

/**
 *
 * @author Jiggyy
 */
public interface IActivityLogService {
    void log(ActivityLogDTO dto);
    
    List<ActivityLogDTO> getLogs();
}
