package com.ajs.shared.dto.category;

import com.ajs.shared.dto.RpcDto;
import com.ajs.shared.dto.item.ItemDetailDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class CategoryDetailDto extends RpcDto {

    private String code;
    private String description;
    private List<ItemDetailDto> itemDtoList;

    private boolean selectedLine;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemDetailDto> getItemDtoList() {
        return itemDtoList;
    }

    public void setItemDtoList(List<ItemDetailDto> itemDtoList) {
        this.itemDtoList = itemDtoList;
    }

    public boolean getSelectedLine() {
        return selectedLine;
    }

    public void setSelectedLine(boolean selectedLine) {
        this.selectedLine = selectedLine;
    }
}
