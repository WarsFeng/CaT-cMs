package cat.wars.cms.manager_course.service.impl;

import cat.wars.cms.framework.domain.course.CourseMarket;
import cat.wars.cms.framework.domain.course.response.CourseCode;
import cat.wars.cms.framework.domain.course.response.CourseMarketResponse;
import cat.wars.cms.framework.exception.ExceptionCast;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.manager_course.dao.CourseMarketRepository;
import cat.wars.cms.manager_course.service.CourseMarketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 5/20/19
 * Time: 12:07 PM
 */

@Service
public class CourseMarketServiceImpl implements CourseMarketService {

    private final CourseMarketRepository repository;

    public CourseMarketServiceImpl(CourseMarketRepository repository) {
        this.repository = repository;
    }

    @Override
    public CourseMarket getById(String id) {
        Optional<CourseMarket> marketOptional = null;
        if (isEmpty(id) || (marketOptional = repository.findById(id)).isEmpty())
            ExceptionCast.cast(CourseCode.COURSE_NOT_EXISTS);
        return marketOptional.get();
    }

    @Override
    public CourseMarketResponse findById(String id) {
        return new CourseMarketResponse(getById(id));
    }

    @Override
    @Transactional
    public ResponseResult save(String id, CourseMarket market) {
        Optional<CourseMarket> marketOptional = null;
        if (isEmpty(id) || (marketOptional = repository.findById(id)).isEmpty()) {
            // Add
            market.setId(id);
            repository.save(market);
        } else { // Update
            CourseMarket dbMarket = marketOptional.get();
            dbMarket.setCharge(market.getCharge());
            dbMarket.setPrice(market.getPrice());
            dbMarket.setValid(market.getValid());
            dbMarket.setStartTime(market.getStartTime());
            dbMarket.setEndTime(market.getEndTime());
            dbMarket.setQq(market.getQq());
            repository.save(dbMarket);
        }
        return ResponseResult.SUCCESS();
    }
}
