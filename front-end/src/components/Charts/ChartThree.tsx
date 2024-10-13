import convertStringForm from "@/util/convertStringForm";
import { ApexOptions } from "apexcharts";
import React from "react";
import ReactApexChart from "react-apexcharts";

interface chartProps {
  title: string;
  data: { [key: string]: number };
}

const ChartThree: React.FC<chartProps> = ({ title, data }) => {
  function getDataOf(type: string) {
    const result = [];

    if (type === "key") {
      for (const key in data) {
        typeof key === "string" && result.push(key);
      }
      return result || undefined;
    }
    if (type === "value") {
      for (const key in data) {
        typeof data[key] === "number" &&
          result.push(convertStringForm(data[key], "toFixed"));
      }
      return result || undefined;
    }
  }

  const colorArray = [
    "#3C50E0", // 기존 블루 계열 1
    "#6577F3", // 기존 블루 계열 2
    "#8FD0EF", // 기존 블루 계열 3
    "#0FADCF", // 기존 블루 계열 4
    "#F3A683", // 피치
    "#F8C291", // 살구
    "#FF6B81", // 코랄
    "#6AB04A", // 연두
    "#F6B93B", // 머스터드
    "#D6A2D6", // 라벤더
  ];

  const series = getDataOf("value") as number[];

  const options: ApexOptions = {
    chart: {
      fontFamily: "Satoshi, sans-serif",
      type: "donut",
    },
    colors: colorArray,
    labels: getDataOf("key") as string[],
    legend: {
      show: false,
      position: "bottom",
    },
    dataLabels: {
      enabled: false,
    },
    responsive: [
      {
        breakpoint: 2600,
        options: {
          chart: {
            width: 380,
          },
        },
      },
      {
        breakpoint: 640,
        options: {
          chart: {
            width: 200,
          },
        },
      },
    ],
    plotOptions: {
      pie: {
        donut: {
          size: "65%",
          background: "transparent",
          labels: {
            show: true,
            name: {
              fontSize: "20",
              formatter(val) {
                return "Total";
              },
            },
            value: {
              fontSize: "30",
              formatter(val) {
                return val;
              },
            },
          },
        },
      },
    },
  };

  const dataListComp = () => {
    const elements = [];
    var index = 0;
    for (const key in data) {
      elements.push(
        <div className="w-full px-8 sm:w-1/2" key={key}>
          <div className="flex w-full items-center">
            <span
              className={`mr-2 block h-3 w-full max-w-3 rounded-full`}
              style={{ background: `${colorArray[index]}` }}
            ></span>
            <p className="flex w-full justify-between text-sm font-medium text-black dark:text-white">
              <span>{key}</span>
              <span>{convertStringForm(data[key], "toFixed")}</span>
            </p>
          </div>
        </div>,
      );
      index++;
    }
    index = 0;
    return elements;
  };

  return (
    <div className="col-span-12 rounded-sm border border-stroke bg-white px-5 pb-5 pt-7.5 shadow-default dark:border-strokedark dark:bg-boxdark sm:px-7.5 xl:col-span-4">
      <div className="mb-3 justify-between gap-4 sm:flex">
        <div>
          <h5 className="text-xl font-semibold text-black dark:text-white">
            {title}
          </h5>
        </div>
        <div>
          <div className="relative z-20 inline-block"></div>
        </div>
      </div>

      <div className="mb-2" style={{ padding: "2rem 0" }}>
        <div id="chartThree" className="mx-auto flex justify-center">
          <ReactApexChart options={options} series={series} type="donut" />
        </div>
      </div>

      <div className="-mx-8 flex flex-wrap items-center justify-center gap-y-3">
        {dataListComp()}
      </div>
    </div>
  );
};

export default ChartThree;
