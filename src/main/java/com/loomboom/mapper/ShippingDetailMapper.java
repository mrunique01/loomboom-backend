package com.loomboom.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.pagination.PaginationReponse;
import com.loomboom.dto.shipping.ShippingDetailRequest;
import com.loomboom.dto.shipping.ShippingDetailResponse;
import com.loomboom.dto.shipping.ShippingDetailsResponse;
import com.loomboom.dto.shipping.UpdateShippingDetailRequest;
import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.Order;
import com.loomboom.model.ShippingDetail;
import com.loomboom.model.User;

import static com.loomboom.contants.ErrorMessage.USER_NOT_EXISTS;
import static com.loomboom.contants.SuccessMessage.*;
import com.loomboom.service.OrderService;
import com.loomboom.service.ShippingDetailService;
import com.loomboom.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShippingDetailMapper {

    private final ShippingDetailService shippingDetailService;
    private final CommonMapper commonMapper;
    private final OrderService orderService;
    private final UserService userService;

    public ShippingDetailResponse createShippingDetail(ShippingDetailRequest shippingDetailRequest) {

        ShippingDetail shippingDetail = commonMapper.mapObject(shippingDetailRequest, ShippingDetail.class);
        return commonMapper.mapObject(shippingDetailService.createShippingDetail(shippingDetail),
                ShippingDetailResponse.class);
    }

    public ShippingDetailsResponse getAllShippingDetail() {
        List<ShippingDetail> shippingDetail = shippingDetailService.getAllShippingDetail();
        List<ShippingDetailResponse> shippingDetails = commonMapper.mapListObject(shippingDetail,
                ShippingDetailResponse.class);
        ShippingDetailsResponse shippingDetailsResponse = new ShippingDetailsResponse();
        shippingDetailsResponse.setShippingDetails(shippingDetails);
        shippingDetailsResponse.setPagination(new PaginationReponse());
        return shippingDetailsResponse;
    }

    public ShippingDetailsResponse getAllShippingDetailByPage(Pageable pageable) {
        Page<ShippingDetail> shippingDetail = shippingDetailService.getAllShippingDetailByPage(pageable);
        List<ShippingDetailResponse> shippingDetails = commonMapper.mapListObject(shippingDetail.getContent(),
                ShippingDetailResponse.class);
        ShippingDetailsResponse shippingDetailResponse = new ShippingDetailsResponse();
        shippingDetailResponse.setShippingDetails(shippingDetails);
        PaginationReponse paginationReponse = commonMapper.paginationData(shippingDetail);
        shippingDetailResponse.setPagination(paginationReponse);
        return shippingDetailResponse;
    }

    public ShippingDetailResponse findShippingDetailById(Long id) {
        return commonMapper.mapObject(shippingDetailService.findById(id), ShippingDetailResponse.class);
    }

    public ShippingDetailResponse updateShippingDetail(Long id,
            UpdateShippingDetailRequest updateShippingDetailRequest) {
        ShippingDetail shippingDetail = commonMapper.mapObject(updateShippingDetailRequest, ShippingDetail.class);
        User user = userService.getUserById(updateShippingDetailRequest.getUserId());
        Order order = orderService.findById(updateShippingDetailRequest.getUserId());
        shippingDetail.setOrder(order);
        shippingDetail.setUsers(user);
        return commonMapper.mapObject(shippingDetailService.updateShippingDetail(shippingDetail, id),
                ShippingDetailResponse.class);
    }

    public ApiResponse deleteShippingDetail(Long id) {
        shippingDetailService.deleteShippingDetailById(id);
        return new ApiResponse(true, ADDRESS_DELETED);
    }
}
