package com.ihsinformatics.covid.fragments.result;

import com.ihsinformatics.covid.R;
import com.ihsinformatics.covid.model.Language;
import com.ihsinformatics.covid.model.Location;

import java.util.ArrayList;
import java.util.List;

public class ResultPresenterImpl implements ResultContract.Presenter {


    private ResultContract.View view;
    private int score;

    @Override
    public void takeView(ResultContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {

    }

    @Override
    public Language getSelectedLangauge() {
        return null;
    }

    @Override
    public void setScore(int score) {
        this.score = score;

        switch (score)
        {
            case 0:
            case 1:
            case 2:
                view.setScoreColor(R.color.green);
                view.setResultDetail(view.getStringResource(R.string.no_indication));
                break;
            case 3:
                view.setScoreColor(R.color.yellow);
                showHighRiskUI(score);
                break;
            case 4:
                view.setScoreColor(R.color.red);
                showHighRiskUI(score);
                break;
        }

    }

    private void showHighRiskUI(int score) {
        view.setResultDetail(String.format(view.getStringResource(R.string.score_detail), score));
        view.showLocations(getLocations());
        view.showContact(getContacts());
    }

    private List<String> getContacts() {
        List<String> contacts = new ArrayList<>();
        contacts.add("021-99204452");
        contacts.add("021-99206565");
        contacts.add("021-99203443");

        return contacts;
    }

    private List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location("Civil Hospital, Karachi","+92 21 99215740","24.859247, 67.010084"));
        locations.add(new Location("Jinnah Hospital, Karachi","+92 21 99201300","24.852211, 67.044245"));
        locations.add(new Location("Dow University OJHA Hospital, Karachi","+92 21 99232660","24.943724, 67.138596"));
        locations.add(new Location("Agha Khan Hospital, Karachi","+92 21 34664931","24.892526, 67.074966"));
        return locations;
    }
}
