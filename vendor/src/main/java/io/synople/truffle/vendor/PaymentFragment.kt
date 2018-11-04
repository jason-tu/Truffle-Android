package io.synople.truffle.vendor


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_payment.*

private const val NUM_PAGES = 2

class PaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = PagerAdapter(fragmentManager!!)
    }

    class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(p0: Int): Fragment {
            when (p0) {
                0 -> return CashFragment.newInstance()
                1 -> return CreditFragment.newInstance()
            }

            return CashFragment.newInstance()
        }

        override fun getCount() = NUM_PAGES
    }

    companion object {
        @JvmStatic
        fun newInstance() = PaymentFragment()
    }
}
