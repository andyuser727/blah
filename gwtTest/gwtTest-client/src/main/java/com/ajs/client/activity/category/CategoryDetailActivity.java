package com.ajs.client.activity.category;

import com.ajs.client.activity.BaseAbstractActivity;
import com.ajs.shared.SimpleResponse;
import com.ajs.shared.commands.LoadCategoryDetail;
import com.ajs.shared.commands.SaveCategoryDetail;
import com.ajs.shared.commands.item.SaveItemDetail;
import com.ajs.shared.dto.category.CategoryDetailDto;
import com.ajs.shared.dto.item.ItemDetailDto;
import com.ajs.client.place.CategoryListPlace;
import com.ajs.shared.AppResponse;
import com.ajs.shared.AppServiceAsync;
import com.ajs.client.ui.category.CategoryDetailViewImpl;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.util.List;

public class CategoryDetailActivity extends BaseAbstractActivity {

    private final AppServiceAsync appServiceAsync;
    private final EventBus eventBus;
    private PlaceController placeController;
    private ListDataProvider<ItemDetailDto> dataProvider;
    private Provider<CategoryListPlace> categoryListPlaceProvider;


    @Inject
    public CategoryDetailActivity(EventBus eventBus, CategoryDetailViewImpl display,
                                  PlaceController placeController,
                                  AppServiceAsync appServiceAsync,
                                  Provider<CategoryListPlace> categoryListPlaceProvider) {
        super(display);
        this.appServiceAsync = appServiceAsync;
        this.eventBus = eventBus;
        this.placeController = placeController;
        this.categoryListPlaceProvider = categoryListPlaceProvider;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {

        container.setWidget(((CategoryDetailViewImpl)display).asWidget());
        String categoryId = (String)getProperties().get("category_id");
        loadCategoryDetails(Long.valueOf(categoryId));
    }

    public void showAddDialog() {

        ((CategoryDetailViewImpl)display).getAddDialog().setText("Add Item");
        ((CategoryDetailViewImpl)display).getAddDialog().center();
        ((CategoryDetailViewImpl)display).getAddDialog().show();
        ((CategoryDetailViewImpl)display).getAddDialog().setVisible(true);
    }


    private void loadCategoryDetails(Long categoryId) {

        LoadCategoryDetail loadCategoryDetail = new LoadCategoryDetail(categoryId);

        appServiceAsync.callServer(loadCategoryDetail,
                new AsyncCallback<AppResponse<CategoryDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(AppResponse<CategoryDetailDto> result) {
                        // TODO seems wrong, read the fucking generics chapter (for the 50th fucking time... a fucking gain)
                        // TODO editor framework
                        ((CategoryDetailViewImpl)display).getCode().setValue((result.getDtos().get(0)).getCode());
                        ((CategoryDetailViewImpl)display).getDescription().setValue((result.getDtos().get(0)).getDescription());
                        setComponents((result.getDtos().get(0)).getItemDtoList());
                        ((CategoryDetailViewImpl)display).getTablePanel().setVisible(true);
                    }
                });
    }

    private void setComponents(List<ItemDetailDto> componentDTOs) {

        if (dataProvider == null) {
            dataProvider = new ListDataProvider<ItemDetailDto>();
            dataProvider.addDataDisplay(((CategoryDetailViewImpl)display).getTable());
        }

        dataProvider.getList().clear();
        dataProvider.getList().addAll(componentDTOs);
        ((CategoryDetailViewImpl)display).getTablePanel().setVisible(componentDTOs.size() > 0);
    }

    public void doExit() {
        CategoryDetailActivity.this.placeController.goTo(categoryListPlaceProvider.get());
    }

    public void doCancel() {
        ((CategoryDetailViewImpl)display).getAddDialog().hide();
    }

    public void doSave() {
        ItemDetailDto itemDto = new ItemDetailDto();
        itemDto.setCode(((CategoryDetailViewImpl)display).getItemCode().getValue());
        itemDto.setDescription(((CategoryDetailViewImpl)display).getItemDescription().getValue());
        SaveItemDetail saveItemDetail = new SaveItemDetail(itemDto, Long.valueOf((String)getProperties().get("category_id")));

        appServiceAsync.callServerSimple(saveItemDetail,
                new AsyncCallback<SimpleResponse>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        System.out.println(caught.getMessage());
                        ((CategoryDetailViewImpl)display).getTablePanel().setVisible(false);
                    }

                    public void onSuccess(SimpleResponse result) {
                        ((CategoryDetailViewImpl)display).getAddDialog().hide();
                        ((CategoryDetailViewImpl)display).getItemCode().setValue(null);
                        ((CategoryDetailViewImpl)display).getItemDescription().setValue(null);
                        loadCategoryDetails(Long.valueOf((String)getProperties().get("category_id")));
                    }
                });
    }

    public void doSaveCat() {

        CategoryDetailDto categoryDetailDto = new CategoryDetailDto();
        categoryDetailDto.setCode(((CategoryDetailViewImpl)display).getCode().getValue());
        categoryDetailDto.setDescription(((CategoryDetailViewImpl)display).getDescription().getValue());
        categoryDetailDto.setId(Long.valueOf((String)getProperties().get("category_id")));
        
        SaveCategoryDetail saveCategoryDetail = new SaveCategoryDetail(categoryDetailDto);

        appServiceAsync.callServerSimple(saveCategoryDetail,
                new AsyncCallback<SimpleResponse>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        System.out.println(caught.getMessage());
                        ((CategoryDetailViewImpl)display).getTablePanel().setVisible(false);
                    }

                    public void onSuccess(SimpleResponse result) {
                        loadCategoryDetails(Long.valueOf((String)getProperties().get("category_id")));
                    }
                });
    }


}
