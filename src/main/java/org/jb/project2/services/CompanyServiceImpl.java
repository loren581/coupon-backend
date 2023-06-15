package org.jb.project2.services;

import lombok.Getter;
import lombok.Setter;
import org.jb.project2.beans.Category;
import org.jb.project2.beans.Company;
import org.jb.project2.beans.Coupon;
import org.jb.project2.exceptions.CouponSystemException;
import org.jb.project2.exceptions.ErrMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@Getter

public class CompanyServiceImpl extends ClientService implements CompanyService {
    private int companyId;

    @Override
    public boolean login(String email, String password) {
        return companyRepository.existsByEmailAndPassword(email, password);
    }

    @Override
    public void addCoupon(Coupon coupon,int id) throws CouponSystemException {
        List<Coupon> coupons = couponRepository.getAllCouponsByCompanyId(id);
        for (Coupon coupon1 : coupons) {
            if (coupon1.getTitle().equals(coupon.getTitle())) {
                throw new CouponSystemException(ErrMessage.CANT_ADD_COUPON);
            }

        }
        couponRepository.save(coupon);

    }

    @Override
    public void deleteCoupon(int couponId) throws CouponSystemException {
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrMessage.COUPON_DOESNT_EXIST);
        }
        couponRepository.deleteById(couponId);
    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException {
        coupon.setId(couponId);
        Coupon couponFromDb = couponRepository.findById(couponId).get();
        if (couponFromDb.getCompany().getId() != coupon.getCompany().getId()) {
            throw new CouponSystemException(ErrMessage.CANT_UPDATE_COUPON_COMPANY_ID);
        }
        couponRepository.saveAndFlush(coupon);

    }

    @Override
    public List<Coupon> getCompanyCoupons() {
        return couponRepository.getAllCouponsByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Category category) {
        return couponRepository.getAllCouponsByCompanyIdAndCategory(companyId, category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Double maxPrice) {
        return couponRepository.getAllCouponsByCompanyIdAndMaxPrice(companyId, maxPrice);
    }

    @Override
    public Company getCompanyDetails() {
        return companyRepository.findById(companyId).get();
    }
}
