package com.ssjm.sw_hackathon.education

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ssjm.sw_hackathon.databinding.FragmentEducationBinding
import com.ssjm.sw_hackathon.education.tab.EduAllFragment
import com.ssjm.sw_hackathon.education.tab.EduBookmarkFragment
import com.ssjm.sw_hackathon.education.tab.EduViewPagerAdapter
import com.ssjm.sw_hackathon.educationApi.bookmark.apiGetBookmark
import com.ssjm.sw_hackathon.educationApi.bookmark.getBookmark.GetBookmarks
import com.ssjm.sw_hackathon.educationApi.openApi.apiGetEducationCount


// 교육 탭
class EducationFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentEducationBinding? = null
    private val binding get() = _binding!!

    // viewPager
    lateinit var viewPagers: ViewPager
    lateinit var tabLayouts: TabLayout

    override fun onResume() {
        super.onResume()

        if(_binding != null) {
            // 서울시 어르신 취업지원센터 교육정보 개수 조회
            apiGetEducationCount(
                addEducationCount = {
                    addEducationCount(it)
                }
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEducationBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.gradient_blue)

        // 서울시 어르신 취업지원센터 교육정보 개수 조회
        apiGetEducationCount(
            addEducationCount = {
                addEducationCount(it)
            }
        )
    }

    var eduCount: Int = 0
    fun addEducationCount(count: Int) {
        eduCount = count
        // 북마크 조회
        apiGetBookmark(
            getBookmark = {
                getBookmarkCnt(it)
            }
        )
    }
    private fun getBookmarkCnt(bookmarks: MutableList<GetBookmarks>?) {
        if(bookmarks == null) initViewPager(eduCount, 0)
        else initViewPager(eduCount, bookmarks.size)
    }


    // viewPager 세팅
    private fun initViewPager(allCount: Int, bookmarkCount: Int) {
        viewPagers = binding.viewpagerEducation
        tabLayouts = binding.tablayoutEducation

        // 교육 탭에 보여줄 Fragment
        val educationAllFragment = EduAllFragment()
        val educationBookmarkFragment = EduBookmarkFragment()

        // 교육 탭 전체/찜 title
        val allTab = "전체 " + allCount
        val bookmarkTab = "찜한 교육 " + bookmarkCount

        // 교육 탭에 각 Fragment 배치
        val adapter = EduViewPagerAdapter(requireActivity().supportFragmentManager)
        adapter.addFragment(educationAllFragment, allTab)
        adapter.addFragment(educationBookmarkFragment, bookmarkTab)

        viewPagers.adapter = adapter
        tabLayouts.setupWithViewPager(viewPagers)

        // Tab 전환
        tabLayouts.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}