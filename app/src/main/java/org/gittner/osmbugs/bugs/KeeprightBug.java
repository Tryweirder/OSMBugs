package org.gittner.osmbugs.bugs;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import org.gittner.osmbugs.App;
import org.gittner.osmbugs.R;
import org.gittner.osmbugs.api.KeeprightApi;
import org.gittner.osmbugs.common.Comment;
import org.gittner.osmbugs.statics.Drawings;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public class KeeprightBug extends Bug {

    public enum STATE {
        OPEN,
        IGNORED,
        IGNORED_TMP
    }

    public KeeprightBug(
            double lat,
            double lon,
            String title,
            String text,
            int type,
            ArrayList<Comment> comments,
            long way,
            int shema,
            int id,
            STATE state) {

        super(title + " <a href=http://www.openstreetmap.org/browse/way/" + way + ">" + way + "</a>",
                text,
                comments,
                new GeoPoint(lat, lon));

        setType(type);
        setSchema(shema);
        setId(id);
        setState(state);
    }

    public KeeprightBug(Parcel parcel) {
        super(parcel);
        mType = parcel.readInt();
        mSchema = parcel.readInt();
        mId = parcel.readInt();
        mWay = parcel.readLong();
        switch (parcel.readInt()) {
            case 1:
                mState = STATE.OPEN;
                break;

            case 2:
                mState = STATE.IGNORED;
                break;

            case 3:
                mState = STATE.IGNORED_TMP;
                break;
        }
    }

    @Override
    public ArrayList<String> getSStates() {
        ArrayList<String> states = new ArrayList<String>();

        states.add(App.getContext().getString(R.string.open));
        states.add(App.getContext().getString(R.string.closed));
        states.add(App.getContext().getString(R.string.ignored));

        return states;
    }

    @Override
    public boolean isCommitable(String newSState, String newComment) { return true; }

    /* Commit the Bug to the Keepright Server */
    @Override
    public boolean commit(String newSState, String newComment) {

        /* Retrieve the new State */
        STATE newState = STATE.OPEN;
        if (newSState.equals(App.getContext().getString(R.string.ignored))) {
            newState = STATE.IGNORED;
        } else if (newSState.equals(App.getContext().getString(R.string.closed))) {
            newState = STATE.IGNORED_TMP;
        }

        return KeeprightApi.comment(mSchema, mId, newComment, newState);
    }

    /* Keepright Bugs can be commented */
    @Override
    public boolean isCommentable() {
        return true;
    }

    /* Get the Bugs State */
    public STATE getState() {
        return mState;
    }

    /* Set the Bugs State */
    public void setState(STATE state) {
        mState = state;
    }

    /* Get the Bugs Type */
    public int getType() {
        return mType;
    }

    /* Set the Bugs Type */
    public void setType(int type) {
        mType = type;
    }

    /* Get the Bugs Schema */
    public int getSchema() {
        return mSchema;
    }

    /* Set the Bugs Schema */
    public void setSchema(int schema) {
        mSchema = schema;
    }

    /* Get the Bugs Id */
    public int getId() {
        return mId;
    }

    /* Set the Bugs Id */
    public void setId(int id) {
        mId = id;
    }

    @Override
    public Drawable getMarker(int bitset) {
        if (mState == STATE.IGNORED_TMP)
            return Drawings.KeeprightDrawableClosed;
        else if (mState == STATE.IGNORED)
            return Drawings.KeeprightDrawableIgnored;
        else {
            switch (mType) {
                case 20:
                    return Drawings.KeeprightDrawable20;
                case 30:
                    return Drawings.KeeprightDrawable30;
                case 40:
                    return Drawings.KeeprightDrawable40;
                case 50:
                    return Drawings.KeeprightDrawable50;
                case 60:
                    return Drawings.KeeprightDrawable60;
                case 70:
                    return Drawings.KeeprightDrawable70;
                case 90:
                    return Drawings.KeeprightDrawable90;
                case 91:
                    return Drawings.KeeprightDrawable91;
                case 92:
                    return Drawings.KeeprightDrawable92;
                case 100:
                    return Drawings.KeeprightDrawable100;
                case 110:
                    return Drawings.KeeprightDrawable110;
                case 120:
                    return Drawings.KeeprightDrawable120;
                case 121:
                    return Drawings.KeeprightDrawable121;
                case 130:
                    return Drawings.KeeprightDrawable130;
                case 140:
                    return Drawings.KeeprightDrawable140;
                case 150:
                    return Drawings.KeeprightDrawable150;
                case 160:
                    return Drawings.KeeprightDrawable160;
                case 170:
                    return Drawings.KeeprightDrawable170;
                case 180:
                    return Drawings.KeeprightDrawable180;
                case 190:
                    return Drawings.KeeprightDrawable190;
                case 191:
                    return Drawings.KeeprightDrawable191;
                case 192:
                    return Drawings.KeeprightDrawable192;
                case 193:
                    return Drawings.KeeprightDrawable193;
                case 194:
                    return Drawings.KeeprightDrawable194;
                case 195:
                    return Drawings.KeeprightDrawable195;
                case 196:
                    return Drawings.KeeprightDrawable196;
                case 197:
                    return Drawings.KeeprightDrawable197;
                case 198:
                    return Drawings.KeeprightDrawable198;
                case 200:
                    return Drawings.KeeprightDrawable200;
                case 201:
                    return Drawings.KeeprightDrawable201;
                case 202:
                    return Drawings.KeeprightDrawable202;
                case 203:
                    return Drawings.KeeprightDrawable203;
                case 204:
                    return Drawings.KeeprightDrawable204;
                case 205:
                    return Drawings.KeeprightDrawable205;
                case 206:
                    return Drawings.KeeprightDrawable206;
                case 207:
                    return Drawings.KeeprightDrawable207;
                case 208:
                    return Drawings.KeeprightDrawable208;
                case 210:
                    return Drawings.KeeprightDrawable210;
                case 211:
                    return Drawings.KeeprightDrawable211;
                case 212:
                    return Drawings.KeeprightDrawable212;
                case 220:
                    return Drawings.KeeprightDrawable220;
                case 221:
                    return Drawings.KeeprightDrawable221;
                case 230:
                    return Drawings.KeeprightDrawable230;
                case 231:
                    return Drawings.KeeprightDrawable231;
                case 232:
                    return Drawings.KeeprightDrawable232;
                case 240:
                    return Drawings.KeeprightDrawable240;
                case 250:
                    return Drawings.KeeprightDrawable250;
                case 260:
                    return Drawings.KeeprightDrawable260;
                case 270:
                    return Drawings.KeeprightDrawable270;
                case 280:
                    return Drawings.KeeprightDrawable280;
                case 281:
                    return Drawings.KeeprightDrawable281;
                case 282:
                    return Drawings.KeeprightDrawable282;
                case 283:
                    return Drawings.KeeprightDrawable283;
                case 284:
                    return Drawings.KeeprightDrawable284;
                case 285:
                    return Drawings.KeeprightDrawable285;
                case 290:
                    return Drawings.KeeprightDrawable290;
                case 291:
                    return Drawings.KeeprightDrawable291;
                case 292:
                    return Drawings.KeeprightDrawable292;
                case 293:
                    return Drawings.KeeprightDrawable293;
                case 300:
                    return Drawings.KeeprightDrawable300;
                case 310:
                    return Drawings.KeeprightDrawable310;
                case 311:
                    return Drawings.KeeprightDrawable311;
                case 312:
                    return Drawings.KeeprightDrawable312;
                case 313:
                    return Drawings.KeeprightDrawable313;
                case 320:
                    return Drawings.KeeprightDrawable320;
                case 350:
                    return Drawings.KeeprightDrawable350;
                case 360:
                    return Drawings.KeeprightDrawable360;
                case 380:
                    return Drawings.KeeprightDrawable380;
                case 390:
                    return Drawings.KeeprightDrawable390;
                case 400:
                    return Drawings.KeeprightDrawable400;
                case 401:
                    return Drawings.KeeprightDrawable401;
                case 402:
                    return Drawings.KeeprightDrawable402;
                case 410:
                    return Drawings.KeeprightDrawable410;
                case 411:
                    return Drawings.KeeprightDrawable411;
                case 412:
                    return Drawings.KeeprightDrawable412;
                case 413:
                    return Drawings.KeeprightDrawable413;
                default:
                    return Drawings.KeeprightDrawableDefault;
            }
        }
    }

    /* Parcelable interface */
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);

        parcel.writeInt(mType);
        parcel.writeInt(mSchema);
        parcel.writeInt(mId);
        parcel.writeLong(mWay);
        switch (mState) {
            case OPEN:
                parcel.writeInt(1);
                break;

            case IGNORED:
                parcel.writeInt(2);
                break;

            case IGNORED_TMP:
                parcel.writeInt(3);
                break;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<KeeprightBug> CREATOR = new Parcelable.Creator<KeeprightBug>() {

        @Override
        public KeeprightBug createFromParcel(Parcel source) {
            return new KeeprightBug(source);
        }

        @Override
        public KeeprightBug[] newArray(int size) {
            return new KeeprightBug[size];
        }
    };

    /* Holds the Bugs state */
    private STATE mState = STATE.OPEN;

    /* Holds the Keepright Type of this Bug */
    private int mType;

    /* Holds the Keepright Schema of this Bug */
    private int mSchema;

    /* Holds the Keepright ID of this Bug */
    private int mId;

    /* Holds the Openstreetmap Way id of this Bug */
    private long mWay;
}
