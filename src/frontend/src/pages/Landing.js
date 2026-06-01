import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './Landing.css';

const DISHES = [
  { img: '🥩', name: 'Bò Wagyu nướng than hoa',  price: '580.000đ', cat: 'Món chính' },
  { img: '🦞', name: 'Tôm hùm hấp bia',           price: '750.000đ', cat: 'Món chính' },
  { img: '🐟', name: 'Cá hồi áp chảo sốt chanh',  price: '320.000đ', cat: 'Món chính' },
  { img: '🍲', name: 'Súp bào ngư vi cá',          price: '185.000đ', cat: 'Khai vị'  },
  { img: '🍫', name: 'Bánh soufflé socola',         price: '125.000đ', cat: 'Tráng miệng' },
  { img: '🍷', name: 'Rượu vang đỏ Pháp',          price: '280.000đ', cat: 'Đồ uống'  },
];

const REVIEWS = [
  { name: 'Nguyễn Văn An', stars: 5, text: 'Bò Wagyu tuyệt vời, chín vừa, mềm tan trong miệng. Không gian sang trọng, nhân viên nhiệt tình.' },
  { name: 'Trần Thị Bích',  stars: 5, text: 'Trải nghiệm ẩm thực đẳng cấp nhất tôi từng có tại TP.HCM. Sẽ quay lại và giới thiệu bạn bè.' },
  { name: 'Lê Minh Khoa',   stars: 5, text: 'Không gian đẹp, món ăn ngon, dịch vụ hoàn hảo. Xứng đáng với từng đồng bỏ ra.' },
];

export default function Landing() {
  const navigate = useNavigate();
  const [scrolled, setScrolled] = useState(false);
  const [menuOpen, setMenuOpen] = useState(false);

  useEffect(() => {
    const onScroll = () => setScrolled(window.scrollY > 60);
    window.addEventListener('scroll', onScroll);
    return () => window.removeEventListener('scroll', onScroll);
  }, []);

  const scrollTo = (id) => {
    document.getElementById(id)?.scrollIntoView({ behavior: 'smooth' });
    setMenuOpen(false);
  };

  return (
    <div className="landing">

      {/* ── Navbar ── */}
      <nav className={`land-nav ${scrolled ? 'scrolled' : ''}`}>
        <div className="land-nav-inner">
          <div className="land-logo" onClick={() => window.scrollTo({top:0,behavior:'smooth'})}>
            <span className="land-logo-icon">🍜</span>
            <span className="land-logo-text">Cái Gì Cũng Không Có</span>
          </div>

          <div className={`land-nav-links ${menuOpen ? 'open' : ''}`}>
            <button onClick={() => scrollTo('about')}>Về chúng tôi</button>
            <button onClick={() => scrollTo('menu-section')}>Thực đơn</button>
            <button onClick={() => scrollTo('reviews')}>Đánh giá</button>
            <button onClick={() => scrollTo('contact')}>Liên hệ</button>
          </div>

          <div className="land-nav-actions">
            <button className="land-btn-outline" onClick={() => navigate('/login')}>
              Đăng nhập
            </button>
            <button className="land-btn-primary" onClick={() => navigate('/register')}>
              Đặt bàn ngay
            </button>
            <button className="land-hamburger" onClick={() => setMenuOpen(!menuOpen)}>
              {menuOpen ? '✕' : '☰'}
            </button>
          </div>
        </div>
      </nav>

      {/* ── Hero ── */}
      <section className="land-hero">
        <div className="hero-bg-overlay"></div>
        <div className="hero-grid"></div>

        <div className="hero-content">
          <div className="hero-badge">✦ Fine Dining · Est. 2026 ✦</div>
          <h1 className="hero-title">
            Cái Gì<br />
            <span className="hero-title-accent">Cũng Không Có</span>
          </h1>
          <p className="hero-subtitle">
            Trải nghiệm ẩm thực đẳng cấp với những nguyên liệu tươi ngon nhất,<br />
            được chế biến bởi đội ngũ đầu bếp hàng đầu tại TP.HCM.
          </p>

          <div className="hero-actions">
            <button className="hero-btn-primary" onClick={() => navigate('/register')}>
              📅 Đặt bàn ngay
            </button>
            <button className="hero-btn-outline" onClick={() => scrollTo('menu-section')}>
              🍽️ Xem thực đơn
            </button>
          </div>

          <div className="hero-stats">
            <div className="hero-stat"><span className="stat-num">500+</span><span className="stat-label">Món ăn</span></div>
            <div className="hero-stat-divider">◆</div>
            <div className="hero-stat"><span className="stat-num">4.9★</span><span className="stat-label">Đánh giá</span></div>
            <div className="hero-stat-divider">◆</div>
            <div className="hero-stat"><span className="stat-num">10K+</span><span className="stat-label">Khách hàng</span></div>
          </div>
        </div>

        <div className="hero-scroll-hint" onClick={() => scrollTo('about')}>
          <span>Khám phá</span>
          <div className="scroll-arrow">↓</div>
        </div>
      </section>

      {/* ── About ── */}
      <section id="about" className="land-about">
        <div className="land-container">
          <div className="section-label">✦ Câu chuyện của chúng tôi ✦</div>
          <h2 className="section-title">Nghệ thuật ẩm thực<br /><span>đỉnh cao</span></h2>
          <p className="section-desc">
            Được thành lập năm 2026, <strong>Cái Gì Cũng Không Có</strong> là điểm đến ẩm thực
            cao cấp tại trung tâm TP.HCM. Chúng tôi kết hợp tinh hoa ẩm thực Á-Âu,
            mang đến những trải nghiệm vị giác độc đáo và không thể quên.
          </p>

          <div className="about-features">
            {[
              { icon: '👨‍🍳', title: 'Đầu bếp 5 sao', desc: 'Đội ngũ đầu bếp được đào tạo tại Pháp và Nhật Bản' },
              { icon: '🌿', title: 'Nguyên liệu tươi', desc: 'Nhập khẩu trực tiếp từ các trang trại hữu cơ hàng đầu' },
              { icon: '🏮', title: 'Không gian sang trọng', desc: 'Thiết kế nội thất độc đáo, phù hợp mọi dịp đặc biệt' },
              { icon: '🍷', title: 'Hầm rượu đặc biệt', desc: 'Bộ sưu tập hơn 200 loại rượu vang từ khắp thế giới' },
            ].map((f, i) => (
              <div key={i} className="about-feature-card">
                <div className="feature-icon">{f.icon}</div>
                <h3>{f.title}</h3>
                <p>{f.desc}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* ── Menu Preview ── */}
      <section id="menu-section" className="land-menu">
        <div className="land-container">
          <div className="section-label">✦ Thực đơn nổi bật ✦</div>
          <h2 className="section-title">Những món ăn<br /><span>được yêu thích nhất</span></h2>

          <div className="menu-preview-grid">
            {DISHES.map((d, i) => (
              <div key={i} className="menu-preview-card">
                <div className="mpcard-img">{d.img}</div>
                <div className="mpcard-info">
                  <span className="mpcard-cat">{d.cat}</span>
                  <h3 className="mpcard-name">{d.name}</h3>
                  <div className="mpcard-footer">
                    <span className="mpcard-price">{d.price}</span>
                    <button className="mpcard-btn" onClick={() => navigate('/register')}>Đặt ngay</button>
                  </div>
                </div>
              </div>
            ))}
          </div>

          <div className="menu-cta">
            <button className="land-btn-primary large" onClick={() => navigate('/register')}>
              Xem toàn bộ thực đơn →
            </button>
          </div>
        </div>
      </section>

      {/* ── Reviews ── */}
      <section id="reviews" className="land-reviews">
        <div className="land-container">
          <div className="section-label">✦ Khách hàng nói gì ✦</div>
          <h2 className="section-title">Đánh giá<br /><span>từ thực khách</span></h2>

          <div className="reviews-grid">
            {REVIEWS.map((r, i) => (
              <div key={i} className="review-card">
                <div className="review-stars">{'★'.repeat(r.stars)}</div>
                <p className="review-text">"{r.text}"</p>
                <div className="review-author">
                  <div className="review-avatar">{r.name.charAt(0)}</div>
                  <span>{r.name}</span>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* ── CTA Banner ── */}
      <section className="land-cta-banner">
        <div className="land-container">
          <h2>Sẵn sàng trải nghiệm?</h2>
          <p>Đặt bàn ngay hôm nay và nhận ưu đãi đặc biệt cho lần đầu tiên</p>
          <div className="cta-banner-actions">
            <button className="hero-btn-primary" onClick={() => navigate('/register')}>
              📅 Đặt bàn ngay
            </button>
            <button className="hero-btn-outline" onClick={() => navigate('/login')}>
              🔑 Đăng nhập
            </button>
          </div>
        </div>
      </section>

      {/* ── Contact / Footer ── */}
      <footer id="contact" className="land-footer">
        <div className="land-container">
          <div className="footer-grid">
            <div className="footer-brand">
              <div className="land-logo">
                <span className="land-logo-icon">🍜</span>
                <span className="land-logo-text">Cái Gì Cũng Không Có</span>
              </div>
              <p>Trải nghiệm ẩm thực đẳng cấp tại trung tâm TP.HCM</p>
            </div>
            <div className="footer-info">
              <h4>Thông tin</h4>
              <p>📍 123 Đường ABC, Quận 1, TP.HCM</p>
              <p>📞 028-xxxx-xxxx</p>
              <p>✉️ hello@cgkc.vn</p>
              <p>🕐 10:00 – 22:00 hàng ngày</p>
            </div>
            <div className="footer-links">
              <h4>Truy cập nhanh</h4>
              <button onClick={() => navigate('/login')}>Đăng nhập</button>
              <button onClick={() => navigate('/register')}>Đăng ký</button>
              <button onClick={() => scrollTo('menu-section')}>Thực đơn</button>
              <button onClick={() => scrollTo('reviews')}>Đánh giá</button>
            </div>
          </div>
          <div className="footer-bottom">
            <span>© 2026 Cái Gì Cũng Không Có. All rights reserved.</span>
            <span>Made with ❤️ in TP.HCM</span>
          </div>
        </div>
      </footer>
    </div>
  );
}
