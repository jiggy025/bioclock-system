/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioclock.client.service;

import bioclock.common.ActivityLogService;
import bioclock.dto.ActivityLogDTO;
import java.rmi.RemoteException;

/**
 *
 * @author Jiggyy
 */
public class RMIActivityLogService {
    
    private final ActivityLogService rmi;
    
    public RMIActivityLogService(ActivityLogService rmi) {
        this.rmi = rmi;
    }
    
    public void log(ActivityLogDTO dto) {
        try {
            rmi.log(dto);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
