package org.gittner.osmbugs.activities;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.gittner.osmbugs.R;
import org.gittner.osmbugs.api.OsmoseApi;
import org.gittner.osmbugs.bugs.OsmoseBug;
import org.gittner.osmbugs.common.OsmoseElement;
import org.gittner.osmbugs.common.OsmoseElementView;

import java.util.List;

@EActivity(R.layout.activity_osmose_edit)
public class OsmoseEditActivity
        extends ActionBarActivity
        implements BugEditActivityConstants
{
    @Extra(EXTRA_BUG)
    OsmoseBug mBug;

    @ViewById(R.id.txtvTitle)
    TextView mTitle;
    @ViewById(R.id.imgvIcon)
    ImageView mIcon;
    @ViewById(R.id.txtvDetailsTitle)
    TextView mDetailsTitle;
    @ViewById(R.id.layoutDetails)
    LinearLayout mDetails;


    @AfterViews
    void init()
    {
        mTitle.setText(mBug.getTitle());

        mIcon.setImageDrawable(mBug.getIcon());

        loadDetails();
    }


    @Background
    void loadDetails()
    {
        List<OsmoseElement> elements = new OsmoseApi().loadElements(mBug.getId());

        detailsLoaded(elements);
    }


    @UiThread
    void detailsLoaded(List<OsmoseElement> elements)
    {
        findViewById(R.id.pbarLoadingDetails).setVisibility(View.GONE);

        if (elements == null || elements.isEmpty())
        {
            mDetailsTitle.setVisibility(View.GONE);
            return;
        }

        mDetailsTitle.setText(R.string.details);

        for (OsmoseElement element : elements)
        {
            OsmoseElementView elementView = new OsmoseElementView(this);
            elementView.set(element);
            mDetails.addView(elementView);
        }
    }
}