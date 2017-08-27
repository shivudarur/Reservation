package com.shiva.reservation.ui.component.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.shiva.reservation.R;
import com.shiva.reservation.TestDataGenerator;
import com.shiva.reservation.model.Customer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by shivananda.darura on 21/08/17.
 */

@RunWith(AndroidJUnit4.class)
public class HomeScreenTest {

    private TestDataGenerator testDataGenerator;

    @Before
    public void setUp() {
        testDataGenerator = new TestDataGenerator();
    }

    @Rule
    public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<HomeActivity>
        (HomeActivity.class, false, false) {
        @Override
        protected Intent getActivityIntent() {
            return super.getActivityIntent();
        }
    };

    @Test
    public void testEmptyListOfCustomers() throws Exception {
        final HomeActivity homeActivity = startActivity();

        //Given
        final List<Customer> customersList = testDataGenerator.getEmptyCustomersList();

        //When
        homeActivity.runOnUiThread(() -> homeActivity.showCustomers(customersList, null));
        //Then
        checkSnackBarDisplayedByResId(R.string.data_loading_error);
    }

    @Test
    public void testSuccessListOfCustomers() throws Exception {
        final HomeActivity homeActivity = startActivity();
        final List<Customer> customersList = testDataGenerator.getCustomersList();

        homeActivity.runOnUiThread(() -> homeActivity.showCustomers(customersList, null));

        int position = 0;
        for (Customer customer : customersList) {
            onView(withId(R.id.rv_customers)).perform(RecyclerViewActions.scrollToPosition(position++));

            final String customerFullName = customer.getCustomerFirstName() + " " + customer.getCustomerLastName();
            onView(withText(customerFullName)).check(matches(isDisplayed()));
        }
    }

    private HomeActivity startActivity() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(context, HomeActivity.class);
        return activityTestRule.launchActivity(intent);
    }

    private void checkSnackBarDisplayedByResId(@StringRes int resourceId) {
        onView(withText(resourceId))
            .check(matches(withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE
            )));
    }

}
