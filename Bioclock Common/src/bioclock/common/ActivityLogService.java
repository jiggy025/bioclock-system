/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioclock.common;

import bioclock.dto.ActivityLogDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Jiggyy
 */
public interface ActivityLogService extends Remote {
    
    void log(ActivityLogDTO dto) throws RemoteException;
    
    List<ActivityLogDTO> getLogs() throws RemoteException;
}
