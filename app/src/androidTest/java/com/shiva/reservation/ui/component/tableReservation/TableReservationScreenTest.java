package com.shiva.reservation.ui.component.tableReservation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.shiva.reservation.R;
import com.shiva.reservation.RecyclerViewMatcher;
import com.shiva.reservation.TestDataGenerator;
import com.shiva.reservation.model.TableMap;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by shivananda.darura on 21/08/17.
 */

@RunWith(AndroidJUnit4.class)
public class TableReservationScreenTest {

    private TestDataGenerator testDataGenerator;

    @Before
    public void setUp() {
        testDataGenerator = new TestDataGenerator();
    }

    @Rule
    public ActivityTestRule<TableReservationActivity> activityTestRule = new ActivityTestRule<TableReservationActivity>
        (TableReservationActivity.class, false, false) {
        @Override
        protected Intent getActivityIntent() {
            return super.getActivityIntent();
        }
    };

    @Test
    public void testEmptyListOfTableMaps() throws Exception {
        final TableReservationActivity tableReservationActivity = startActivity();

        //Given
        final List<TableMap> tableMapsList = testDataGenerator.getEmptyTableMapsList();

        //When
        tableReservationActivity.runOnUiThread(() -> tableReservationActivity.showTableMaps(tableMapsList, null));
        //Then
        checkSnackBarDisplayedByResId(R.string.data_loading_error);
    }

    @Test
    public void testSuccessListOfTableMaps() throws Exception {
        final TableReservationActivity tableReservationActivity = startActivity();
        final List<TableMap> tableMapsList = testDataGenerator.getTableMapsList();

        tableReservationActivity.runOnUiThread(() -> tableReservationActivity.showTableMaps(tableMapsList, null));

        int position = 0;
        for (TableMap tableMap : tableMapsList) {
            onView(withId(R.id.rv_table_maps)).perform(RecyclerViewActions.scrollToPosition(position));
            final Boolean isTableReserved = tableMap.getTableReserved();
            String textToBeDisplayedOnTableCell = tableReservationActivity
                .getString(isTableReserved ? R.string.reserved : R.string.tap_to_reserve);
            onView(new RecyclerViewMatcher((R.id.rv_table_maps)).atPosition(position))
                .check(matches(hasDescendant(withText(textToBeDisplayedOnTableCell))));
            position++;
        }
    }

    private TableReservationActivity startActivity() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(context, TableReservationActivity.class);
        return activityTestRule.launchActivity(intent);
    }

    private void checkSnackBarDisplayedByResId(@StringRes int resourceId) {
        onView(withText(resourceId))
            .check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE
            )));
    }

}
