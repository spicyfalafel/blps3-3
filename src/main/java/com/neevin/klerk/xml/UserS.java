package com.neevin.klerk.xml;

import com.neevin.klerk.entity.User;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/*
this shitty approach is the only way to store list of object into xml in JAXB xml library
 */
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserS {
    @XmlElement(name="user")
    List<User> users;
}

