package com.wherismyvehicle.whereismyvehicle.Presenters;

import com.wherismyvehicle.whereismyvehicle.Models.User;

import java.util.ArrayList;

public class ProfileFragmentPresenter {
    private User user;
    private View view;

    public ProfileFragmentPresenter(View view) {
        this.view = view;

        // TODO: Load user
        this.user = new User();
    }

    public interface View {

    }
}