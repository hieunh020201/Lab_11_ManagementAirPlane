package main;

import entity.*;
import repository.AddressRepository;
import repository.CategoryRepository;
import repository.ProductRepository;
import repository.UserRepository;
import util.JdbcConnectionDBUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
        UserRepository userRepository = new UserRepository();
        AddressRepository addressRepository = new AddressRepository();
        CategoryRepository categoryRepository = new CategoryRepository();
        ProductRepository productRepository = new ProductRepository();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Sign in (Nhập số 1)");
            System.out.println("Sign up (Nhập số 2)");
            String enter = scanner.nextLine();
            if (enter.equals("1")) {
                System.out.print("Username: ");
                String usernameLogin = scanner.nextLine();
                System.out.print("Password: ");
                String passwordLogin = scanner.nextLine();
                ResponseLogin responseLogin = null;
                responseLogin = userRepository.login(usernameLogin, passwordLogin);
                if (responseLogin.getUserName() != null && responseLogin.getPassword() != null) {
                    user = userRepository.getUserByUserName(responseLogin.getUserName());
                    if (user.getRole().equals("ADMIN"))
                        while (true) {
                            System.out.println("Danh sách danh mục");
                            System.out.println();
                            List<Category> categories = categoryRepository.getListCategory();
                            categories.stream().forEach(category -> {
                                System.out.println("ID: " + category.getId() + ", name: " + category.getName());
                            });

                            System.out.println("Thêm danh mục (Nhập số 0)");
                            System.out.println("Thực hiện sửa, xóa với danh mục (Nhập ID)");
                            System.out.println("Exit (Nhập Q)");

                            String selection = scanner.nextLine();
                            if (selection.equals("0")) {
                                System.out.println("Tạo danh mục mới");
                                System.out.println();
                                System.out.print("Name: ");
                                String name = scanner.nextLine();
                                if (categoryRepository.insertCategory(name) == 1) {
                                    System.out.print("Tạo danh mục thành công");
                                } else {
                                    System.out.print("Tạo danh mục thất bại");
                                }
                            } else if (selection.equals("Q")) {
                                break;
                            } else {
                                categories.stream().forEach(category -> {
                                    if (selection.equals(String.valueOf(category.getId()))) {
                                        while (true) {
                                            System.out.println("Xóa danh mục (Nhập số 1)");
                                            System.out.println("Sửa danh mục (Nhập số 2)");
                                            System.out.println("Exit (Nhập Q)");

                                            String optionCategory = scanner.nextLine();
                                            if (optionCategory.equals("1")) {
                                                try {
                                                    if (categoryRepository.deleteCategory(category.getId()) == 1) {
                                                        System.out.println("Xóa danh mục thành công");
                                                    } else {
                                                        System.out.println("Xóa danh mục thất bại");
                                                    }
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            } else if (optionCategory.equals("2")) {
                                                System.out.println("Chỉnh sửa danh mục");
                                                System.out.println();
                                                System.out.print("Name: ");
                                                String name = scanner.nextLine();

                                                category.setName(name);
                                                try {
                                                    if (categoryRepository.updateCategory(category) == 1) {
                                                        System.out.println("Xóa danh mục thành công");
                                                    } else {
                                                        System.out.println("Xóa danh mục thất bại");
                                                    }
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            } else if (optionCategory.equals("Q")) {
                                                break;
                                            }else {
                                                System.out.println("Enter Your Option Again!");
                                            }
                                        }
                                    }
                                });

                            }
                        }
                    else if (user.getRole().equals("USER")){
                        while (true) {
                            System.out.println("Chỉnh sửa thông tin cá nhân (Nhập số 1)");
                            System.out.println("Thêm địa chỉ (Nhập số 2)");
                            System.out.println("Chỉnh sửa thông tin địa chỉ (Nhập số 3)");
                            System.out.println("Xem danh mục (Nhập số 4)");
                            System.out.println("Xem sản phẩm (Nhập số 5)");
                            System.out.println("Logout (Nhập Q)");
                            String selectionUser = scanner.nextLine();
                            if (selectionUser.equals("Q")) {
                                user = new User();
                                break;
                            }
                            switch (selectionUser) {
                                case "1" : {
                                    System.out.print("Username: ");
                                    String username = scanner.nextLine();
                                    System.out.print("Name: ");
                                    String name = scanner.nextLine();
                                    System.out.print("Phone: ");
                                    String phone = scanner.nextLine();
                                    System.out.print("Gender: ");
                                    String gender = scanner.nextLine();
                                    System.out.print("Email: ");
                                    String email = scanner.nextLine();
                                    System.out.print("BirthDay: ");
                                    String birthday = scanner.nextLine();
                                    user.setUserName(username);
                                    user.setName(name);
                                    user.setPhone(phone);
                                    user.setGender(gender);
                                    user.setEmail(email);
                                    user.setBirthday(Date.valueOf(birthday));
                                    if (userRepository.UpdateAddress(user) == 1) {
                                        System.out.println("Cập nhật thông tin thành công!");
                                    } else {
                                        System.out.println("Cập nhật thông tin thất bại!");
                                    }
                                    break;
                                }
                                case "2" : {
                                    System.out.print("Name: ");
                                    String name = scanner.nextLine();
                                    System.out.print("Phone: ");
                                    String phone = scanner.nextLine();
                                    System.out.print("Province: ");
                                    String province = scanner.nextLine();
                                    System.out.print("District: ");
                                    String district = scanner.nextLine();
                                    System.out.print("Street: ");
                                    String street = scanner.nextLine();
                                    System.out.print("Type: ");
                                    String type = scanner.nextLine();
                                    System.out.print("Default_address: ");
                                    String default_address = scanner.nextLine();
                                    Address address = new Address(name, phone, province, district,
                                            street, Boolean.valueOf(type), Boolean.valueOf(default_address));
                                    if (addressRepository.AddNewAddress(user.getId(), address) == 1) {
                                        System.out.println("Thêm địa chỉ thành công");
                                    } else {
                                        System.out.println("Thêm địa chỉ thất bại");
                                    }
                                    System.out.println();
                                    break;
                                }
                                case "3" : {
                                    while (true) {
                                        System.out.println("Danh sách các địa chỉ:");
                                        List<Address> addresses = addressRepository.getListAddressById(user.getId());

                                        addresses.stream().forEach(address -> {
                                            System.out.println("ID: " + address.getId() + ", Tên: " + address.getName() + ", Phone: " + address.getPhone() +
                                                    ", Address: " + address.getStreet() + ", " + address.getDistrict() + ", " +
                                                    address.getProvince() + ", default: " + address.isDefaultAddress());
                                        });


                                        System.out.println("Chọn địa chỉ muốn thay đổi (Nhập ID)");
                                        System.out.println("Exit (Enter Q)");

                                        String enterAddress = scanner.nextLine();
                                        if (enterAddress.equals("Q")) {
                                            break;
                                        }
                                        addresses.stream().forEach(address -> {
                                            if (enterAddress.equals(String.valueOf(address.getId()))) {
                                                Address address1 = null;
                                                try {
                                                    address1 = addressRepository.getAddressById(address.getId());
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                                System.out.print("Name: ");
                                                String name = scanner.nextLine();
                                                System.out.print("Phone: ");
                                                String phone = scanner.nextLine();
                                                System.out.print("Province: ");
                                                String province = scanner.nextLine();
                                                System.out.print("District: ");
                                                String district = scanner.nextLine();
                                                System.out.print("Street: ");
                                                String street = scanner.nextLine();
                                                System.out.print("Type: ");
                                                String type = scanner.nextLine();
                                                System.out.print("Default_address: ");
                                                String default_address = scanner.nextLine();

                                                address1.setName(name);
                                                address1.setPhone(phone);
                                                address1.setProvince(province);
                                                address1.setDistrict(district);
                                                address1.setStreet(street);
                                                address1.setType(Boolean.valueOf(type));
                                                address1.setDefaultAddress(Boolean.valueOf(default_address));

                                                try {
                                                    if (addressRepository.UpdateAddress(user.getId(), address1) == 1) {
                                                        System.out.println("Thay đổi địa chỉ thành công");
                                                    } else {
                                                        System.out.println("Thay đổi địa chỉ thất bại");
                                                    }
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                        });
                                    }
                                    break;
                                }
                                case "4" : {
                                    while (true) {
                                        System.out.println("Danh sách danh mục:");
                                        System.out.println();
                                        List<Category> categories = categoryRepository.getListCategory();
                                        categories.stream().forEach(category -> {
                                            System.out.println("ID: " + category.getId() + ", name: " + category.getName());
                                        });

                                        System.out.println("Xem danh sách sản phẩm theo danh mục (Nhập id)");
                                        System.out.println("Exit (Nhập Q)");
                                        String selection = scanner.nextLine();
                                        if (selection.equals("Q")) {
                                            break;
                                        }
                                        categories.stream().forEach(category -> {
                                            if (selection.equals(String.valueOf(category.getId()))) {
                                                try {
                                                    List<Product> products = productRepository.getListProductByCategoryId(category.getId());
                                                    products.stream().forEach(product -> {
                                                        System.out.println("Sản phẩm: " + product.getName() + ", Giá: " + product.getPrice() +
                                                                ", Người bán: " + product.getSellerName() + ", Danh mục: " + product.getCategoryId());
                                                    });
                                                } catch (SQLException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                        });
                                    }
                                    break;
                                }
                                case "5" : {
                                    List<Product> products = productRepository.getListProduct();
                                    products.stream().forEach(product -> {
                                        System.out.println("Sản phẩm: " + product.getName() + ", Giá: " + product.getPrice() +
                                                ", Người bán: " + product.getSellerName() + ", Danh mục: " + product.getCategoryId());
                                    });
                                    break;
                                }
                                default: {
                                    System.out.println("Enter Your Option Again!!");
                                }
                            }
                        }
                    }
                }
                else {
                    System.out.println("Đăng nhập thất bại!");
                }
            } else if (enter.equals("2")) {
                System.out.println("Nhập thông tin để đăng ký tài khoản");
                System.out.print("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password: ");
                String password = scanner.nextLine();
                System.out.println("Enter name: ");
                String name = scanner.nextLine();
                System.out.println("Enter phone: ");
                String phone = scanner.nextLine();
                System.out.println("Enter gender: ");
                String gender = scanner.nextLine();
                System.out.println("Enter email: ");
                String email = scanner.nextLine();
                System.out.println("Enter birthday: ");
                String birthday = scanner.nextLine();

                User newUser = new User();
                newUser.setUserName(username);
                newUser.setPassword(password);
                newUser.setName(name);
                newUser.setPhone(phone);
                newUser.setGender(gender);
                newUser.setEmail(email);
                newUser.setBirthday(Date.valueOf(birthday));

                if (userRepository.insertUser(newUser) == 1) {
                    System.out.println("Đăng ký thành công!");
                } else {
                    System.out.println("Đăng ký thất bại!");
                }

            }

        }
    }

    public static User user = new User();
}
