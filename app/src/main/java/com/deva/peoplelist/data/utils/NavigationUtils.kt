import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.deva.peoplelist.ui.activities.PeopleList
import com.deva.peoplelist.ui.compose.PeopleDetailUi
import com.deva.peoplelist.ui.compose.UserDetailUi
import com.deva.peoplelist.viewmodel.PeopleViewModel

@Composable
fun NavigationUtils(
    peopleViewModel: PeopleViewModel = viewModel()
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.PeopleList.route) {
        composable(route = Screen.PeopleList.route) {
            PeopleList(
                peopleViewModel,
                navController
            )
        }
        composable(
            route = Screen.PeopleDetails.route + "?peopleId={peopleId}",
            arguments = listOf(navArgument("peopleId"){
                type = NavType.StringType
            })
        ) {
           backStackEntry ->
            val userId : Int = backStackEntry.arguments?.getString("peopleId")!!.toInt()
            val people = peopleViewModel.getPeopleByIndex(userId)
            PeopleDetailUi(navController = navController, people = people)
        }

        composable(
            route = Screen.UserDetails.route + "?userId={userId}",
            arguments = listOf(navArgument("userId"){
                type = NavType.StringType
            })
        ) {
                backStackEntry ->
            val userId : Int = backStackEntry.arguments?.getString("userId")!!.toInt()
            val user = peopleViewModel.getUserByUserID(userId)
            UserDetailUi(navController = navController, user = user)
        }
    }

}


sealed class Screen(val route: String) {
    data object PeopleList: Screen("peoples")
    data object PeopleDetails: Screen("peoples/people_detail")
    data object UserDetails: Screen("peoples/user_detail")
}