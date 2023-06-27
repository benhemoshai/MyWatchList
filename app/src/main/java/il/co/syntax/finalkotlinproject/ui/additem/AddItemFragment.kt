package il.co.syntax.finalkotlinproject.ui.additem


//import MovieViewModel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import il.co.syntax.finalkotlinproject.data.models.Item
import il.co.syntax.finalkotlinproject.ui.ItemViewModel
import  il.co.syntax.finalkotlinproject.R
import il.co.syntax.finalkotlinproject.data.repository.MovieRepository
import java.util.Calendar
import java.util.Date
import il.co.syntax.finalkotlinproject.databinding.AddItemLayoutBinding
import il.co.syntax.finalkotlinproject.ui.single_movie.SingleMovieViewModel
import java.lang.Thread.sleep
import java.text.SimpleDateFormat
import java.util.*
@AndroidEntryPoint
class AddItemFragment : Fragment() {

    private val viewModel: ItemViewModel by activityViewModels()
    private val additemviewmodel: AddItemViewModel by activityViewModels()

    // private val movieviewmodel : MovieViewModel by activityViewModels()
    private var selectedDate: Date? = null
    private var _binding: AddItemLayoutBinding? = null
    private val binding get() = _binding!!

    private var imgURL: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddItemLayoutBinding.inflate(inflater, container, false)

       // Glide.with(requireContext()).load(R.drawable.movienight).into(binding.resultImage)

        //observes the changes in the date
        additemviewmodel.date.observe(viewLifecycleOwner) { date ->
            binding.date.text = date
        }
        additemviewmodel.movieName.observe(viewLifecycleOwner) { movieTitle ->
            binding.MovieName.text = movieTitle.toString()
        }


        additemviewmodel.image.observe(viewLifecycleOwner) {
            Glide.with(requireContext()).load(it).into(binding.resultImage)
            imgURL = it

        }


        //when the user clicks on finish button - checks if he selected a date and a movie
        //if not, it shows a toast message. if yes it creates an item which includes
        //a movie name,description, date and an image.

        binding.finishBtn.setOnClickListener {

            if (binding.MovieName.text.toString() == getString(R.string.movie_title)) {
                Toast.makeText(requireContext(), getString(R.string.movie_toast), Toast.LENGTH_LONG)
                    .show()
            } else if (binding.date.text.toString() == getString(R.string.date_button)) {
                Toast.makeText(requireContext(), getString(R.string.date_toast), Toast.LENGTH_LONG)
                    .show()
            } else {


                val item = Item(
                    binding.MovieName.text.toString(),
                    binding.itemDescription.text.toString(),
                    binding.date.text.toString(),
                    imgURL
                )
                viewModel.addItem(item)
                findNavController().navigate(R.id.action_addItemFragment_to_allItemsFragment)
                additemviewmodel.setImage("https://img.freepik.com/free-vector/cinema-background-design_1294-87.jpg?w=826&t=st=1687531244~exp=1687531844~hmac=d8d60faa1a65d4f1e2e57801ba119b73dc10c0413ad324ccefdb2553a1a98061")
                additemviewmodel.setDate(getString(R.string.date_button))
                additemviewmodel.setMovieName(getString(R.string.movie_title))

                  }
            }
            //when the user clicks on the date button - opens a date dialog
            binding.date.setOnClickListener {
                showDatePicker()
            }


            //when the user clicks on "pick a movie" button - moves to the movie list fragment
            binding.movieButton.setOnClickListener {
                findNavController().navigate(R.id.action_addItemFragment_to_allMoviesFragment)
            }



            return binding.root

        }



        private fun showDatePicker() {
            val calendar = Calendar.getInstance()
            val currentYear = calendar.get(Calendar.YEAR)
            val currentMonth = calendar.get(Calendar.MONTH)
            val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(year, month, day)
                    selectedDate = selectedCalendar.time

                    updateSelectedDateView()
                },
                currentYear,
                currentMonth,
                currentDay
            )

            // Disable past dates
            datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000

            datePickerDialog.show()
        }

        private fun updateSelectedDateView() {
            if (selectedDate != null) {
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate!!)
                additemviewmodel.setDate(formattedDate)
            }
        }


        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }


    }
