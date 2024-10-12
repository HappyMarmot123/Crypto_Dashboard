export default function convertStringForm(value: number, form: string) {
  if (form === "comma") {
    return value.toLocaleString();
  }
  if (form === "percentage") {
    return parseFloat(value.toFixed(3)) + "%";
  }
  if (form === "converter") {
    if (value >= 1e12) {
      return (value / 1e12).toFixed(2) + "T"; // 조 (Trillion)
    } else if (value >= 1e9) {
      return (value / 1e9).toFixed(2) + "B"; // 억 (Billion)
    } else if (value >= 1e6) {
      return (value / 1e6).toFixed(2) + "M"; // 백만 (Million)
    } else if (value >= 1e3) {
      return (value / 1e3).toFixed(2) + "K"; // 천 (Thousand)
    } else {
      return value.toString();
    }
  }
  if (form === "date") {
    const date = new Date(value * 1000);
    const formattedDate = date
      .toISOString()
      .slice(0, 10)
      .replace("T", "YYYY-MM-DD");
    return formattedDate;
  }
}
