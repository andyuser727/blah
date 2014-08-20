package com.ajs.shared.commands;

import com.ajs.shared.dto.RpcDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 15/12/2013
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
public class ProcessCategoryDeletes extends RpcDto {

     List<Long> idsToDelete;
     boolean allSelected;

     public ProcessCategoryDeletes(){
     }

    public ProcessCategoryDeletes(List<Long> idsToDelete, boolean allSelected){
        this.allSelected = allSelected;
        this.idsToDelete = idsToDelete;
    }

    public List<Long> getIdsToDelete() {
        return idsToDelete;
    }

    public boolean isAllSelected() {
        return allSelected;
    }
}
