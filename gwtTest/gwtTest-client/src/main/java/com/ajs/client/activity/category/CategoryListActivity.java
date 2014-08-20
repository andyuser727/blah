package com.ajs.client.activity.category;

import java.util.ArrayList;
import java.util.List;

import com.ajs.client.activity.BaseAbstractActivity;
import com.ajs.shared.SimpleResponse;
import com.ajs.shared.commands.LoadCategoryList;
import com.ajs.shared.commands.ProcessCategoryDeletes;
import com.ajs.shared.dto.category.CategoryDetailDto;
import com.ajs.shared.commands.SaveCategoryDetail;
import com.ajs.client.image.AppResources;
import com.ajs.client.place.CategoryDetailPlace;
import com.ajs.shared.AppResponse;
import com.ajs.shared.AppServiceAsync;
import com.ajs.client.ui.category.CategoryListViewImpl;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.*;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class CategoryListActivity extends BaseAbstractActivity<CategoryListViewImpl> {

    private final AppServiceAsync appServiceAsync;
    private final EventBus eventBus;
    private PlaceController placeController;
    private ListDataProvider<CategoryDetailDto> dataProvider;
    private boolean deleteClicked;
    private boolean deleteCheckBoxClicked;
    List<CategoryDetailDto> categoryDetailDtos;
    private Provider<CategoryDetailPlace> categoryDetailPlaceProvider;

    @Inject
    public CategoryListActivity(EventBus eventBus, CategoryListViewImpl display,
                                PlaceController placeController,
                                AppServiceAsync appServiceAsync,
                                Provider<CategoryDetailPlace> categoryDetailPlaceProvider) {
        super(display);
        this.appServiceAsync = appServiceAsync;
        this.eventBus = eventBus;
        this.placeController = placeController;
        this.categoryDetailPlaceProvider = categoryDetailPlaceProvider;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {

        bind();
        container.setWidget(display.asWidget());
        display.getTablePanel().add(display.getTable());
        display.getTablePanel().add(display.getPager());
        display.getPager().setDisplay(display.getTable());
        loadCategories();
    }

    public void bind() {

        CellTable<CategoryDetailDto> table = new CellTable<CategoryDetailDto>();

        CheckboxCell selectForDelete = new CheckboxCell(false, false) {
            @Override
            public void render(com.google.gwt.cell.client.Cell.Context context, Boolean value, SafeHtmlBuilder sb) {
                CategoryDetailDto dto = (CategoryDetailDto) context.getKey();
                super.render(context, dto.getSelectedLine(), sb);
            }
        };

        Column<CategoryDetailDto, Boolean> selectedForDeleteColumn = new Column<CategoryDetailDto, Boolean>(selectForDelete) {
            @Override
            public Boolean getValue(CategoryDetailDto object) {
                return object.getSelectedLine();
            }
        };
        selectedForDeleteColumn.setFieldUpdater(new FieldUpdater<CategoryDetailDto, Boolean>() {
            @Override
            public void update(int index, CategoryDetailDto object, Boolean value) {
                object.setSelectedLine(value);
                setSelected(value, object);
                deleteCheckBoxClicked = true;
                display.getSelectAll().setValue(false);
            }
        });

        table.addColumn(selectedForDeleteColumn);

        TextColumn<CategoryDetailDto> codeColumn = new TextColumn<CategoryDetailDto>() {
            @Override
            public String getValue(CategoryDetailDto dto) {
                return dto.getCode();
            }
        };

        table.addColumn(codeColumn);

        TextColumn<CategoryDetailDto> descriptionColumn = new TextColumn<CategoryDetailDto>() {
            @Override
            public String getValue(CategoryDetailDto dto) {
                return dto.getDescription();
            }
        };

        table.addColumn(descriptionColumn);

        Column<CategoryDetailDto, ImageResource> deleteColumn = new Column<CategoryDetailDto, ImageResource>(new ImageResourceCell()) {
            @Override
            public ImageResource getValue(CategoryDetailDto object) {
                if (object != null) {
                    ImageResource deleteIcon = AppResources.INSTANCE.deleteIcon();
                    return AppResources.INSTANCE.deleteIcon();
                }

                return null;
            }

            @Override
            public void onBrowserEvent(Cell.Context context, Element elem, CategoryDetailDto object, NativeEvent event) {
                super.onBrowserEvent(context, elem, object, event);

                if ("click".equals(event.getType())) {
                    if (object != null) {
                        deleteClicked = true;
                        Window.alert("deleting item");
                        loadCategories();
                    }
                }
            }
        };
        table.addColumn(deleteColumn);

        display.setTable(table);
        
        final SingleSelectionModel<CategoryDetailDto> selectionModel = new SingleSelectionModel<CategoryDetailDto>();
        display.getTable().setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                CategoryDetailDto selected = selectionModel.getSelectedObject();
                if (selected != null && !deleteCheckBoxClicked && !deleteClicked) {
                    selectionModel.setSelected(selected, false);
                    CategoryDetailPlace categoryDetailPlace = categoryDetailPlaceProvider.get();
                    // TODO use constructor with map
                    categoryDetailPlace.getProperties().put("category_id", String.valueOf(selected.getId()));
                    categoryDetailPlace.getProperties().put("test_id", String.valueOf(7L));
                    placeController.goTo(categoryDetailPlace);
                }
                deleteClicked = false;
                deleteCheckBoxClicked = false;
            }
        });

    }

    public void processDeletes() {

        List<Long> idsToDelete = new ArrayList<Long>();
        for (CategoryDetailDto categoryDetailDto : categoryDetailDtos) {
            if (categoryDetailDto.getSelectedLine()) {
                idsToDelete.add(categoryDetailDto.getId());
            }
        }
        ProcessCategoryDeletes processCategoryDeletes = new ProcessCategoryDeletes(idsToDelete, display.getSelectAll().getValue().booleanValue());
        appServiceAsync.callServerSimple(processCategoryDeletes,
                new AsyncCallback<SimpleResponse>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(SimpleResponse result) {
                        loadCategories();
                        display.getSelectAll().setValue(false);
                    }
                });
    }

    private void setSelected(Boolean selected, CategoryDetailDto object) {
        object.setSelectedLine(selected);
    }

    public void doSelectAll(Boolean selectAll) {

        deleteCheckBoxClicked = true;

        for (CategoryDetailDto categoryDetailDto : categoryDetailDtos) {
            categoryDetailDto.setSelectedLine(selectAll);
        }

        display.getTable().redraw();
    }

    static class ImageResourceCell extends AbstractCell<ImageResource> {

        ImageResourceCell() {
            super("click");
        }

        // needed??
        @Override
        public boolean handlesSelection() {
            return false;
        }

        @Override
        public void render(Context context, ImageResource value, SafeHtmlBuilder sb) {
            if (value != null) {
                SafeHtml html = SafeHtmlUtils.fromTrustedString(
                        AbstractImagePrototype.create(value).getHTML());
                sb.append(html);
            }
        }
    }

    void loadCategories() {
        LoadCategoryList loadCategoryList = new LoadCategoryList();

        appServiceAsync.callServer(loadCategoryList,
                new AsyncCallback<AppResponse<CategoryDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        display.getTablePanel().setVisible(false);
                    }

                    public void onSuccess(AppResponse<CategoryDetailDto> result) {
                        categoryDetailDtos = result.getDtos();
                        if (categoryDetailDtos != null && categoryDetailDtos.size() > 0) {
                            setComponents();
                            display.getTablePanel().setVisible(true);
                        }
                    }
                });
    }

    public void showAddDialog() {

        display.getAddDialog().setText("Add Category");
        display.getAddDialog().center();
        display.getAddDialog().show();
        display.getAddDialog().setVisible(true);
    }

    private void setComponents() {

        if (dataProvider == null) {
            dataProvider = new ListDataProvider<CategoryDetailDto>();
            dataProvider.addDataDisplay(display.getTable());
        }

        dataProvider.getList().clear();
        dataProvider.getList().addAll(categoryDetailDtos);
        display.getTablePanel().setVisible(categoryDetailDtos.size() > 0);
    }

    public void doSave(){
        CategoryDetailDto categoryDetailDto = new CategoryDetailDto();
        categoryDetailDto.setCode(display.getCode().getValue());
        categoryDetailDto.setDescription(display.getDescription().getValue());
        SaveCategoryDetail saveCategoryDetail = new SaveCategoryDetail(categoryDetailDto);
        appServiceAsync.callServerSimple(saveCategoryDetail,
                new AsyncCallback<SimpleResponse>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        System.out.println(caught.getMessage());
                        display.getTablePanel().setVisible(false);
                    }

                    public void onSuccess(SimpleResponse result) {
                        display.getAddDialog().hide();
                        display.getCode().setValue(null);
                        display.getDescription().setValue(null);
                        loadCategories();
                    }
                });
    }

    public void doCancel(){
        display.getAddDialog().hide();
    }

    @Override
    public void onStop() {
        if (dataProvider != null && display.getTable() != null) {
            dataProvider.removeDataDisplay(display.getTable());
        }
    }

}
