package com.ajs.client.mvp;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class InjectablePlaceController extends PlaceController {

    private Place lastPlace = Place.NOWHERE;

    @Inject
	public InjectablePlaceController(EventBus eventBus) {
		super(eventBus);
	}

    @Override
    public void goTo(Place newPlace) {
        lastPlace = getWhere();
        super.goTo(newPlace);
    }

    public Place getLastPlace() {
        return lastPlace;
    }
}
