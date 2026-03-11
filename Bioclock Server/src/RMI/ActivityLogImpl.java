/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import Service.IActivityLogService;
import bioclock.common.ActivityLogService;
import bioclock.dto.ActivityLogDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author Jiggyy
 */
public class ActivityLogImpl extends UnicastRemoteObject implements ActivityLogService  {

    private final IActivityLogService logService;
    
    public ActivityLogImpl(IActivityLogService logService) throws RemoteException{
        this.logService = logService;
    }
    
    @Override
    public void log(ActivityLogDTO dto) {
        logService.log(dto);
    }

    @Override
    public List<ActivityLogDTO> getLogs() {
        return logService.getLogs();
    }
    
}
