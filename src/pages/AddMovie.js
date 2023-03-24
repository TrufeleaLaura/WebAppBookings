import React, { useState } from "react";
import { Form, Input, Select, Button } from "antd";
import axios from "axios";

const { Option } = Select;

const categories = [
  "COMEDY",
  "DRAMA",
  "HORROR",
  "ROMANCE",
  "ANIMATION",
  "CRIME",
  "DOCUMENTARY",
  "FAMILY",
];

const AddMovie = () => {
  const [form] = Form.useForm();
  const [imageUrl, setImageUrl] = useState("");

  const onFinish = async (values) => {
    const { name, category, type } = values;

    try {
      await axios.post(`http://localhost:8080/movies/addMovie`, {
        name,
        category,
        type,
        imageUrl,
      });

      form.resetFields();
      setImageUrl("");
      alert("Movie added successfully!");
    } catch (error) {
      console.error(error);
      alert("Failed to add movie!");
    }
  };

  const handleImageUrlChange = (event) => {
    setImageUrl(event.target.value);
  };

  const validateImageUrl = (_, value) => {
    if (!value) {
      return Promise.reject(new Error("Please input an image URL!"));
    }

    if (!/^(ftp|http|https):\/\/[^ "]+$/.test(value)) {
      return Promise.reject(new Error("Please input a valid image URL!"));
    }

    return Promise.resolve();
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh",
        backgroundImage:
          "url('https://thumbs.dreamstime.com/b/clapperboard-pop-corn-orange-color-background-cinema-film-concept-fresh-salty-movie-clapper-board-banner-145416811.jpg')",
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      <Form
        form={form}
        onFinish={onFinish}
        layout="vertical"
        style={{
          width: "50%",
          padding: "90px",
          backgroundColor: "#fff",
          borderRadius: "20px",
        }}
      >
        <Form.Item name="name" label="Name" rules={[{ required: true }]}>
          <Input />
        </Form.Item>
        <Form.Item
          name="category"
          label="Category"
          rules={[{ required: true }]}
        >
          <Select>
            {categories.map((category) => (
              <Option key={category} value={category}>
                {category}
              </Option>
            ))}
          </Select>
        </Form.Item>
        <Form.Item name="type" label="Type" rules={[{ required: true }]}>
          <Select>
            <Option value="TWOD">2D</Option>
            <Option value="THREED">3D</Option>
          </Select>
        </Form.Item>
        <Form.Item
          name="imageUrl"
          label="Image URL"
          rules={[{ required: true, validator: validateImageUrl }]}
        >
          <Input onChange={handleImageUrlChange} />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">
            Add
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default AddMovie;
