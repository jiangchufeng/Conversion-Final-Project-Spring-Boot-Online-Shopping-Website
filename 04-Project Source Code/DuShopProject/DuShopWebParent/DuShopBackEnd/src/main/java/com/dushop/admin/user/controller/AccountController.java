package com.dushop.admin.user.controller;

import java.io.IOException;

import com.dushop.admin.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dushop.admin.FileUploadUtil;
import com.dushop.admin.security.DuShopUserDetails;
import com.dushop.common.entity.User;

/*
 *@BelongsProject: DuShopProject
 *@BelongsPackage: com.dushop.admin.user
 *@Author: Jiang Chufeng
 *@CreateTime: 2022-07-23  17:20
 *@Description: TODO logged user information update
 *@Version: 1.0
 */

@Controller
public class AccountController {
    @Autowired
    private UserService service;

    @GetMapping("/account")
    public String viewDetails(@AuthenticationPrincipal DuShopUserDetails loginUser,
                              Model model) {
        String email = loginUser.getUsername();
        User user = service.getByEmail(email);
        model.addAttribute("user", user);

        return "users/account_form";

    }

    @PostMapping("/account/update")
    public String saveDetails(User user, RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal DuShopUserDetails loginUser,
                              @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPhotos(fileName);
            User savedUser = service.updateAccount(user);

            String uploadDirectory = "user-photos/" + savedUser.getId();

            FileUploadUtil.cleanDir(uploadDirectory);
            FileUploadUtil.saveFile(uploadDirectory, fileName, multipartFile);

        } else {
            if (user.getPhotos().isEmpty()) user.setPhotos(null);
            service.updateAccount(user);
        }

        loginUser.setFirstName(user.getFirstName());
        loginUser.setLastName(user.getLastName());

        redirectAttributes.addFlashAttribute("message", "Account details have been updated successfully.");

        return "redirect:/account";
    }
}
